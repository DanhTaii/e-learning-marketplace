document.querySelectorAll(".tab-item").forEach(tab => {
    tab.addEventListener("click", function () {
        const target = this.dataset.tab;

        // active tab header
        document.querySelectorAll(".tab-item").forEach(t => t.classList.remove("active"));
        this.classList.add("active");

        // show content
        document.querySelectorAll(".tab-pane").forEach(p => p.classList.remove("active"));
        document.getElementById(target).classList.add("active");
    });
});