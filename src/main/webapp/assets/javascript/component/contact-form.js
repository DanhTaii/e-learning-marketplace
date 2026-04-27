const contactBtn = document.getElementById("contactBtn");
const modal = document.getElementById("contactModal");
const closeBtn = document.getElementById("closeContactModal");
const cancelBtn = document.getElementById("cancelContact");

contactBtn.onclick = () => {
    modal.classList.add("active");
};

closeBtn.onclick = () => {
    modal.classList.remove("active");
};

cancelBtn.onclick = () => {
    modal.classList.remove("active");
};

modal.querySelector(".contact-modal__overlay").onclick = () => {
    modal.classList.remove("active");
};