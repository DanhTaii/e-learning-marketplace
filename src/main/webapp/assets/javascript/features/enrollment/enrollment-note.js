// ========================= TẠO NOTE MỚI ========================

document.addEventListener('DOMContentLoaded', function () {
    const btnSaveNote = document.getElementById('btn-save-note')
    const cloudinaryPlayer = document.getElementById('cloudinaryPlayer')
    const currentTimeDisplay = document.getElementById('current-video-time-display');
    const noteInput = document.getElementById('note-content-input');
    const noteContainer = document.getElementById('notes-list-container');

    // LIÊN TỤC CẬP NHẬT THỜI GIAN
    initVideoTimer({cloudinaryPlayer, currentTimeDisplay})

    if (btnSaveNote) {
        initSaveNoteButton({btnSaveNote, noteInput, cloudinaryPlayer})
    }

    initNoteAction({noteContainer})

})

// =================== CẬP NHẬT THỜI GIAN TRÊN PHẦN EDIT NOTE ===================
function initVideoTimer({cloudinaryPlayer, currentTimeDisplay}) {
    if (!cloudinaryPlayer || !currentTimeDisplay) return;

    cloudinaryPlayer.addEventListener('timeupdate', () => {
        const currentSeconds = Math.floor(cloudinaryPlayer.currentTime)

        currentTimeDisplay.innerText = formatTime(currentSeconds);
    })
}

// =================== THỰC HIỆN LƯU TRỮ NOTE MỚI ===================
function initSaveNoteButton({btnSaveNote, noteInput, cloudinaryPlayer}) {

    if (!btnSaveNote || !cloudinaryPlayer) return;

    btnSaveNote.addEventListener('click', function () {
        const content = noteInput.value.trim();
        const currentLessonId = PlayerState.currentLessonId

        // Lấy số giây hiện tại
        const noteTime = Math.floor(cloudinaryPlayer.currentTime);

        // Vô hiệu hóa nút bấm
        btnSaveNote.innerHTML = '<i class="fa-solid fa-spinner fa-spin"></i> Đang lưu...';
        btnSaveNote.disabled = true;

        fetch('personal/my-course/note', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: `lessonId=${currentLessonId}&noteTime=${noteTime}&content=${encodeURIComponent(content)}`
        })
            .then(response => {
                return response.json()
            })
            .then(data => {
                if (data.status === 'success') {
                    // Thành công: Xóa trắng ô nhập liệu và báo thành công
                    noteInput.value = "";
                    console.log("Đã lưu ghi chú thành công tại giây: " + noteTime);
                    loadLessonNotes(PlayerState.currentLessonId)
                } else {
                    alert("Có lỗi xảy ra, không thể lưu ghi chú!");
                }
            })
            .catch(error => {
                console.error('Lỗi khi lưu ghi chú:', error);
                alert("Lỗi kết nối mạng!");
            })
            .finally(() => {
                // Phục hồi lại trạng thái ban đầu của nút Lưu
                btnSaveNote.innerHTML = '<i class="fa-regular fa-floppy-disk"></i> Lưu ghi chú';
                btnSaveNote.disabled = false;
            });
    })
}

// =================== LOAD LIST DATA NOTE TỪ SERVER ===================
function loadLessonNotes(lessonId) {
    const container = document.getElementById('notes-list-container');
    const emptyState = document.getElementById('empty-note-state');

    fetch(`personal/my-course/note?lessonId=${lessonId}`)
        .then(response => {
            return response.json()
        })
        .then(data => {
            // Xóa danh sách cũ đi
            container.innerHTML = '';

            if (data.length === 0) {
                // Nếu ko có ghi chú thì hiện ảnh trống có sẵn bên JSP
                container.appendChild(emptyState);
                return;
            }

            container.insertAdjacentHTML('beforeend',
                `<div class="header-note-list-container mb-2">Ghi chú đã lưu ( ${data.length} )</div>`
            );
            // nếu có data thì tạo vòng lặp để chèn dữ liệu vào HTML
            data.forEach(note => {
                renderNoteCard(container, note)
            })
        })
}

// =================== Render HTML Note Card ===================
function renderNoteCard(container, note) {
    // Tạo ra 1 note-card và lấy dữ liệu từ note hiện tại truyền vào
    const noteHtml = `
                    <div class="note-card" data-note-id="${note.id}">
                        <div class="note-card-header">
                            <div class="note-time-badge-saved" onclick="seekToTime(${note.noteTime})">
                                <i class="fa-regular fa-clock"></i> ${formatTime(note.noteTime)}
                            </div>
                            
                            <div class="note-actions">
                                <button class="note-action-btn edit" title="Sửa ghi chú" 
                                        data-edit-id="${note.id}" data-action="edit">
                                    <i class="fa-solid fa-pen-to-square"></i>
                                </button>
                                <button class="note-action-btn delete" title="Xóa ghi chú" 
                                        data-delete-id="${note.id}" data-action="delete">
                                    <i class="fa-solid fa-trash-can"></i>
                                </button>
                            </div>
                        </div>
                        <div class="note-card-content">${note.content}</div>
                    </div>
                `;
    // Chèn note-card trên vào thẻ chứa là container (có sẵn bên JSP)
    // và nằm sau nội dung hiện đang có bên JSP với: beforeend (tất nhiên là có afterbegin)
    container.insertAdjacentHTML('beforeend', noteHtml);
}

// =================== Tua thời gian video đến đúng thời gian được truyền vào ===================
function seekToTime(seconds) {
    const cloudinaryPlayer = document.getElementById("cloudinaryPlayer");
    if (cloudinaryPlayer) {
        // Lấy thời gian hiện tại của video gán thành thời gian truyền vào
        cloudinaryPlayer.currentTime = seconds;
        cloudinaryPlayer.play();
        // Cuộn màn hình lên đầu video để xem cho tiện
        window.scrollTo({top: 100, behavior: 'smooth'});
    }
}

function initNoteAction({noteContainer}) {
    if (!noteContainer) return;

    noteContainer.addEventListener('click', (event) => {

        const deleteBtn = event.target.closest('[data-delete-id]');

        if (deleteBtn) {
            event.stopPropagation();
            // data-delete-id thì lúc lấy ra laf dataset.deleteId và luôn là String
            const noteId = Number(deleteBtn.dataset.deleteId)
            deleteNote(noteId);
        }

    })
}

function deleteNote(noteId) {
    setupConfirmModal({
        action: 'delete_note',

        mode: 'ajax',

        url: 'personal/my-course/note/action',

        body: {
            action: 'delete',
            noteId: noteId
        },

        onSuccess: () => {
            loadLessonNotes(
                PlayerState.currentLessonId
            );
        }
    })
}