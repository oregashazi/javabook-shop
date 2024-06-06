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



const carts = document.querySelectorAll(".book__cart");
const cartContainer = document.querySelector(".header__overflow");
const closeButton = document.getElementById("close-cart");
console.log(closeButton);

    carts.forEach(cart => {
        cart.addEventListener("click", (e) => {
            if (cartContainer.classList.contains("-close")) {
                var tl = gsap.timeline();

                // document.querySelector(".offcanvas-backdrop").classList.add("show");
                tl.to("body", { overflow: "hidden", duration: 0.1 });
                tl.to(cartContainer, {
                    position: "fixed",
                    duration: 0,
                });
                tl.set(".cart__container", {
                    display: "block",
                });
                tl.to(".cart__container", {
                    bottom: "auto",
                    duration: 0.5
                });
                cartContainer.classList.add("-open");
                cartContainer.classList.remove("-close");
            }
        });
    })

if (closeButton) {
    closeButton.addEventListener("click", (e) => {
        const tl = gsap.timeline();
        tl.to(".cart__container", {
            bottom: "-100%",
            duration: 0.5,
            delay: 0.2
        });
        tl.set(".cart__container", {
            display:"none",
        })
        // tl.to(".offcanvas-backdrop", { className: "-=show", duration: 0.2 });
        tl.to(cartContainer, {
            position: "static",
            duration: 0,
        });
        // document.querySelector(".offcanvas-backdrop").remove();
        tl.to("body", { overflow: "auto", duration: 0 });
        cartContainer.classList.add("-close");
        cartContainer.classList.remove("-open");
    });
}


document.querySelector(".search__form").addEventListener("submit", (e) => {
    e.preventDefault();

    // Hide search Bar

    if (searchEl.classList.contains("--is-visible")) {
        searchEl.classList.add("--is-hidden");
    }

    const urlParams = new URLSearchParams(window.location.search);
    const sortParam = urlParams.get("sort");
    const searchValue = document.querySelector(".search__input").value;

    urlParams.delete("s");

    if (searchValue != " ") {
        urlParams.append("s", searchValue);
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
        });

})

const searchEl= document.querySelector(".search__el");
const closeSearchButton = document.querySelector(".search__close-button");
const searchButtons = document.querySelectorAll(".search__button");
// var offCanvas = document.querySelector(".offcanvas");

searchButtons.forEach(button => {
    button.addEventListener("click", function () {
        if (searchEl.classList.contains("--is-hidden")) {
            searchEl.classList.remove("--is-hidden");
        }
        searchEl.classList.add("--is-visible");
    });
});

closeSearchButton.addEventListener("click", () => {
    searchEl.classList.remove("--is-visible");
    searchEl.classList.add("--is-hidden");

    if (document.querySelector(".offcanvas-backdrop")) {
        document.querySelector(".offcanvas-backdrop").remove();
    }
});

var openMenu = document.querySelector(".menu__open-button");
var closeMenu = document.querySelector(".menu__close-button");
var menu = document.querySelector(".menu__container");
var menuBackground = document.querySelector(".menu__background");

openMenu.addEventListener("click", () => {
    if (menu.classList.contains("--is-hidden")) {
        menu.classList.remove("--is-hidden");
        menu.classList.add("--is-visible");
        menuBackground.classList.remove("--is-hidden");
        menuBackground.classList.add("--is-visible");
    }
});

closeMenu.addEventListener("click", () => {
    if (menu.classList.contains("--is-visible")) {
        menu.classList.remove("--is-visible");
        menu.classList.add("--is-hidden");
        menuBackground.classList.remove("--is-visible");
        menuBackground.classList.add("--is-hidden");
    }
})


document.querySelectorAll(".quantity__input").forEach(el => {
    let timeout = null;

    el.addEventListener("input", (e) => {
        e.preventDefault();

        if (timeout !== null) {
            clearTimeout(timeout);
        }

        timeout = setTimeout(() => {
            const target = e.target;
            const id = parseInt(target.getAttribute("data-id-produkt"));
            const value = target.value;

            fetch(window.location.origin + `/cart/setquantity/${id}/${value}`)
                .then(response => {
                    location.reload();
                })


        }, 1000);
    });
});

document.querySelectorAll(".cart__button").forEach(button => {
    let timeout = null;

    button.addEventListener("click", (e) => {
        e.preventDefault();

        if (timeout !== null) {
            clearTimeout(timeout);
        }

        const target = e.target.parentElement.closest('.input-group').querySelector(".quantity__input");

        timeout = setTimeout(() => {
            const id = parseInt(target.getAttribute("data-id-produkt"));
            const value = parseInt(target.value);

            fetch(window.location.origin + `/cart/setquantity/${id}/${value}`)
                .then(response => {
                    location.reload();
                })

        }, 1000);
    })
})

var rabatt = 0;

document.addEventListener("DOMContentLoaded", () => {
    const totalPrice = document.querySelector("#total__price");
    const lieferungPrice = 3.99 + parseFloat(totalPrice.innerText);
    const grandTotalPrice = document.querySelector("#grand-total__price");
    grandTotalPrice.innerText = lieferungPrice.toFixed(2);

    const cardDiscount = document.querySelectorAll(".cart__discount");

    cardDiscount.forEach(discount => {
        if (parseInt(discount.getAttribute("data-discount")) === 0) {
            discount.style.display = "none";
        } else {
            const closest = discount.closest(".cart-item");
            const discountEL = parseInt(discount.getAttribute("data-discount"));
            const originalPrice = parseFloat(closest.querySelector(".price").getAttribute("data-currency-usd"));
            const finalPrice = originalPrice - ((originalPrice * discountEL) / 100);
            const roundedFinalPrice = Math.round(finalPrice * 100) / 100;
            closest.querySelector(".total-price span").innerText = `$${roundedFinalPrice}`;

            rabatt = rabatt + (originalPrice - roundedFinalPrice);
        }
    })

    document.querySelector(".books__total-rabatt").innerText = rabatt.toFixed(2);
    const newTotal = (parseFloat(document.querySelector("#grand-total__price").innerText) - rabatt.toFixed(2)).toFixed(2);
    document.querySelector("#grand-total__price").innerText = newTotal;
})


document.querySelectorAll(".cart-delete__book").forEach(button => {
    button.addEventListener("click", (e) => {
        const target = e.target.closest(".cart-cross-outline");

        console.log(target);

        const id = parseInt(target.getAttribute("data-id-produkt"));
        console.log(id);
        fetch(window.location.origin + `/cart/remove/${id}`)
            .then(response => {
                location.reload();
            })
    });
});



// CHECKOUT


// if (document.querySelector("#checkout")) {
//
//     fetch(window.location.origin + "/cart.html")
//         .then(response => response.text())
//         .then(html => {
//             const parser = new DOMParser();
//             const newDoc = parser.parseFromString(html, 'text/html');
//             const newBooksContainer = newDoc.querySelector('.pb-3');
//
//             const currentCartTotals = document.querySelector(".cart-totals");
//             currentCartTotals.innerHTML = newBooksContainer.innerHTML;
//         })
//
// }


