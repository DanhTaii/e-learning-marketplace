const menuBtn = document.getElementById("menu-toggle");
const sidebar = document.querySelector(".container-1");
const overlay = document.getElementById("sidebar-overlay");

menuBtn.addEventListener("click", () => {
    sidebar.classList.toggle("active");
    overlay.classList.toggle("active");
});

overlay.addEventListener("click", () => {
    sidebar.classList.remove("active");
    overlay.classList.remove("active");
});
