// ===========================================
// READY CAR - SCRIPT DEL CARRITO
// ===========================================

const carrito = document.querySelector("#carrito");
const listaCarrito = document.querySelector("#lista-carrito tbody");
const vaciarCarritoBtn = document.querySelector("#vaciar-carrito");
const finalizarCompraBtn = document.querySelector("#finalizar-compra");
const productos = document.querySelector(".box-container");

let articulosCarrito = [];

const carritoGuardado = sessionStorage.getItem("carritoReadyCar");

if (carritoGuardado) {

    articulosCarrito = JSON.parse(carritoGuardado);

}

// ===========================================
// EVENTOS
// ===========================================

cargarEventos();

actualizarCarrito();

function cargarEventos() {

    if (productos) {
        productos.addEventListener("click", agregarProducto);
    }

    if (carrito) {
        carrito.addEventListener("click", eliminarProducto);
    }

    if (vaciarCarritoBtn) {

        vaciarCarritoBtn.addEventListener("click", function (e) {

            e.preventDefault();

            articulosCarrito = [];

            actualizarCarrito();

        });

    }

 if (finalizarCompraBtn) {

    finalizarCompraBtn.addEventListener("click", function (e) {

        e.preventDefault();

        if (articulosCarrito.length === 0) {

            alert("El carrito está vacío.");

            return;

        }

        // Convertir el carrito a texto JSON
        const carritoJSON = JSON.stringify(articulosCarrito);

        // Colocar el JSON en el input oculto
        document.getElementById("carritoInput").value = carritoJSON;

        // Enviar el formulario al Servlet
        document.getElementById("formCompra").submit();

    });

}

}

// ===========================================
// AGREGAR PRODUCTO
// ===========================================

function agregarProducto(e) {

    e.preventDefault();

    if (!e.target.classList.contains("agregar-carrito")) {
        return;
    }

    const card = e.target.closest(".producto-card");

    leerDatosProducto(card);

}

// ===========================================
// LEER PRODUCTO
// ===========================================

function leerDatosProducto(card) {

    const producto = {

        id: card.querySelector(".agregar-carrito").dataset.id,
        nombre: card.querySelector("h3").textContent,
        precio: card.querySelector(".precio").textContent,
        imagen: card.querySelector("img").src,
        cantidad: 1

    };

    const existe = articulosCarrito.some(item => item.id === producto.id);

    if (existe) {

        articulosCarrito = articulosCarrito.map(item => {

            if (item.id === producto.id) {
                item.cantidad++;
            }

            return item;

        });

    } else {

        articulosCarrito.push(producto);

    }

    actualizarCarrito();

}

// ===========================================
// ACTUALIZAR CARRITO
// ===========================================

function actualizarCarrito() {

    limpiarCarrito();

    articulosCarrito.forEach(producto => {

        const fila = document.createElement("tr");

        fila.innerHTML = `

            <td><img src="${producto.imagen}" width="55"></td>

            <td>${producto.nombre}</td>

            <td>${producto.precio}</td>

            <td>${producto.cantidad}</td>

            <td>
                <a href="#"
                   class="borrar"
                   data-id="${producto.id}">
                   X
                </a>
            </td>

        `;

        listaCarrito.appendChild(fila);

    });

sessionStorage.setItem("carritoReadyCar", JSON.stringify(articulosCarrito));

}

// ===========================================
// ELIMINAR PRODUCTO
// ===========================================

function eliminarProducto(e) {

    e.preventDefault();

    if (!e.target.classList.contains("borrar")) {
        return;
    }

    const id = e.target.dataset.id;

    articulosCarrito = articulosCarrito.filter(producto => producto.id !== id);

    actualizarCarrito();

}

// ===========================================
// LIMPIAR TABLA
// ===========================================

function limpiarCarrito() {

    while (listaCarrito.firstChild) {

        listaCarrito.removeChild(listaCarrito.firstChild);

    }

}