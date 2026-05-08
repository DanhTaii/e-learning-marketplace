document.querySelectorAll(".tab-item").forEach(tab => {
    tab.addEventListener("click", function () {
        const target = this.dataset.tab;

        document.querySelectorAll(".tab-item").forEach(tab => tab.classList.remove("active"));
        this.classList.add("active")

        document.querySelectorAll(".tab-pane").forEach(pane => pane.classList.remove("active"))
        document.getElementById(target).classList.add("active")
    })
})