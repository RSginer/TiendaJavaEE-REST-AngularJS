(function() {
    angular.module('app-productos', ["directivas-productos"])

    .controller("ControladorProductos", ['$http', function($http) {
        tienda = this;
        tienda.productos = [];

        //       [{
        //            nombre: "Adidas SuperStar",
        //            descripcion: "La línea adidas Originals es una creación reciente del gran fabricante adidas. Lejos quedan ya los primeros días de la marca, cuando Adi Dassler fabricaba a mano sus primeros zapatos, instalado en los 20 m² de la lavandería de su madre. Las deportivas adidas llevan casi un siglo equipando atletas de todas clases con el mejor material posible. ",
        //            imagen: "img/zapatillasAdidas.jpg",
        //            precio: 89,
        //            agotado: false,
        //            reviews: [
        //                {
        //                    estrellas: 4,
        //                    autor: "Rubén",
        //                    imagen: "img/user.jpg",
        //                    comentario: "Son muy buenas !"
        //                },
        //                {
        //                    estrellas: 2,
        //                    autor: "Alfonso",
        //                    imagen: "img/user.jpg",
        //                    comentario: "Vaya mierda de zapatillas se me rompieron en un dia"
        //                }
        //            ]
        //        }]

        $http.get('http://localhost:8080/TiendaJavaEE/api/productos').success(function(data) {
            tienda.productos = data;
        });
    }]);


})();
