const input = document.getElementById("search-input");
const suggestionsBox = document.getElementById("searchSuggestions");


input.addEventListener("keyup", function () {
    let keyword = input.value.trim();

    if (keyword.length === 0) {
        suggestionsBox.style.display = "none";
        suggestionsBox.innerHTML = "";
        return;
    }

    fetch("result-search/by-title?ajax=true&keyword=" + encodeURIComponent(keyword), {
        headers: {
            'X-CSRF-Token': getCsrfToken()
        }
    })
        .then(response => response.json())
        .then(data => {
            suggestionsBox.innerHTML = "";

            if (data.length === 0) {
                suggestionsBox.style.display = "none";
                return;
            }

            data.forEach(course => {
                console.log(course);

                let div = document.createElement("div");
                div.classList.add("suggestion__item");

                // Gán nội dung
                div.innerHTML = `
                    <img class="suggestion__img" src="${course.thumbnailUrl}">
                    <div class="suggestion__title"><c:out value="${course.title}"/></div>
                    <div class="suggestion__price"><c:out value="${course.price - course.discountPrice}"/>đ</div>
                `;

                div.addEventListener("click", function (e) {
                    e.preventDefault();
                    console.log("Clicked course:", course.id, course.title);
                    window.location.href = "course-detail?id=" + course.id;
                });

                suggestionsBox.appendChild(div);
            });

            suggestionsBox.style.display = "block";
        })
        .catch(err => {
            console.error("Fetch suggestions error:", err);
            suggestionsBox.style.display = "none";
        });

});
