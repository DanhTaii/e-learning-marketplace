if (localStorage.getItem("darkMode") === "true") {
    document.body.classList.add("dark-mode");
}

const btn = document.querySelector('.switch-mode');

if (btn) {

    if (document.body.classList.contains("dark-mode")) {
        btn.classList.add("active");
    }

    btn.addEventListener('click', function(e) {
        e.preventDefault();

        document.body.classList.toggle('dark-mode');
        this.classList.toggle('active');

        localStorage.setItem(
            "darkMode",
            document.body.classList.contains("dark-mode")
        );
    });
}