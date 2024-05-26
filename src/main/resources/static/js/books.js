const el = document.querySelector(".book__container");

if (el) {
    discountPrice();
    const sortingSelect = document.getElementById("sorting");

    document.getElementById("sorting").addEventListener("change", applySorting);

    function applySorting() {
        const urlParams = new URLSearchParams(window.location.search);
        const selectedValue = sortingSelect.value;

        urlParams.delete("sort");

        if (selectedValue !== "DEFAULT") {
            urlParams.append("sort", selectedValue);
        }

        window.history.replaceState({}, '', `${location.pathname}?${urlParams}`);

        fetch(window.location.href)
            .then(response => response.text())
            .then(html => {
                const parser = new DOMParser();
                const newDoc = parser.parseFromString(html, 'text/html');
                const newBooksContainer = newDoc.getElementById('booksContainer');
                if (newBooksContainer) {
                    const currentBooksContainer = document.getElementById('booksContainer');
                    currentBooksContainer.innerHTML = newBooksContainer.innerHTML;
                }
                discountPrice();
            })

    }
}


function discountPrice() {
    document.querySelectorAll(".book__element").forEach(el => {
        const discount = parseFloat(el.querySelector(".discount").innerText);
        const price = parseFloat(el.querySelector(".price").innerText);
        const originalPrice = el.querySelector(".original-price");

        if (discount > 0) {
            originalPrice.textContent = price;
            const finalPrice = price - ((price * discount) / 100);
            const roundedFinalPrice = Math.round(finalPrice * 100) / 100;
            el.querySelector(".price").innerText = roundedFinalPrice;
        } else {
            el.querySelector(".discount__container").style.display = "none";
        }
    })
}


document.addEventListener("DOMContentLoaded", () => {
    showActiveFilter();
});



function showActiveFilter() {
    const urlParams = new URLSearchParams(window.location.search);
    const sortParam = urlParams.get("sort");

    if (sortParam) {
        const sortingSelect = document.getElementById("sorting");
        sortingSelect.value = sortParam;
    }
}



const cart = document.querySelector(".book__cart");
const cartContainer = document.querySelector(".header__overflow");
const closeButton = document.getElementById("close-cart");
console.log(closeButton);

if (cartContainer) {
    cart.addEventListener("click", (e) => {
        if (cartContainer.classList.contains("-close")) {
            var tl = gsap.timeline();

            // if (document.querySelector(".offcanvas") && document.querySelector(".offcanvas").classList.contains("show")) {
            //     document.querySelector(".offcanvas").classList.remove("show");
            //     document.querySelector(".offcanvas-backdrop").classList.remove("show");
            // }

            // document.querySelector(".offcanvas-backdrop").classList.add("show");

            tl.to("body", { overflow: "hidden", duration: 0.1 });
            tl.to(cartContainer, {
                position: "fixed",
                duration: 0,
            });
            tl.to(".cart__container", {
                bottom: "auto",
                duration: 0.5
            });
            cartContainer.classList.add("-open");
            cartContainer.classList.remove("-close");
        }
    });
}

if (closeButton) {
    closeButton.addEventListener("click", (e) => {
        const tl = gsap.timeline();
        tl.to(".cart__container", {
            bottom: "-100%",
            duration: 0.5,
            delay: 0.2
        });
        // tl.to(".offcanvas-backdrop", { className: "-=show", duration: 0.2 });
        tl.to(cartContainer, {
            position: "static",
            duration: 0,
        });
        // document.querySelector(".offcanvas-backdrop").remove();
        tl.to("body", { position: "relative", overflow: "auto", duration: 0 });
        cartContainer.classList.add("-close");
        cartContainer.classList.remove("-open");
    });
}


document.querySelector(".search-form").addEventListener("submit", (e) => {
    e.preventDefault();
    console.log(e);

    const urlParams = new URLSearchParams(window.location.search);
    const sortParam = urlParams.get("sort");
    const searchValue = document.querySelector("#search-form").value;

    urlParams.delete("s");

    if (searchValue != " ") {
        urlParams.append("s", searchValue);
    }

    window.history.replaceState({}, '', `${location.pathname}?${urlParams}`);

    // Hide Search Bar

    document.querySelector(".search-popup").classList.remove("is-visible");

    // fetch(window.location.href)
    //     .then(response => response.text())
    //     .then(html => {
    //         const parser = new DOMParser();
    //         const newDoc = parser.parseFromString(html, 'text/html');
    //         const newBooksContainer = newDoc.getElementById('booksContainer');
    //         if (newBooksContainer) {
    //             const currentBooksContainer = document.getElementById('booksContainer');
    //             currentBooksContainer.innerHTML = newBooksContainer.innerHTML;
    //         }
    //         discountPrice();
    //     });

})

