(function () {
    angular.module("directivas-productos", [])
            .directive("listadoProductos", function () {
                return{
                    restrict: 'E',
                    templateUrl: './templates/includes/productos/lista-productos.html',
                    controller: function () {
                        
                        this.repetirPuntuacionMedia = function (producto) {
                            this.repeticiones = [];
                            this.media = 0;
                            for (var i = 0; i < producto.reviews.length; i++) {
                                this.media = this.media + producto.reviews[i].estrellas;
                            }
                            this.media = Math.round(this.media / producto.reviews.length);
                            for (var i = 0; i < this.media; i++) {
                                this.repeticiones.push(i);
                            }
                            return this.repeticiones;
                        };
                    },
                    controllerAs: 'productos'
                };
            })
            .directive("tabsProducto", function () {
                return{
                    restrict: 'E',
                    templateUrl: 'templates/includes/productos/tabs-producto.html',
                    controller: function () {
                        this.tab = 1;
                        this.setTab = function (tab) {
                            this.tab = tab;
                        };
                        this.isSet = function (tab) {
                            return this.tab === tab;
                        };
                    },
                    controllerAs: 'tab'
                };
            })
            .directive("descripcionProducto", function () {
                return{
                    restrict: 'E',
                    templateUrl: 'templates/includes/productos/descripcion-producto.html'
                };
            })
            .directive("opinionesProducto", function () {
                return{
                    restrict: 'E',
                    templateUrl: 'templates/includes/productos/opiniones-producto.html',
                    scope: {
                        p: "=producto"
                    },
                    controller: function () {
                        this.limite = 2;
                        this.aumentaLimite = function () {
                            this.limite += 3;
                        };
                        this.opinionEnviada = false;
                        this.setOpinionEnviada = function (o) {
                            this.opinionEnviada = o;
                        };
                        this.opinion = {
                            estrellas: 5,
                            autor: 'Anónimo', imagen: "img/user.jpg",
                            fecha: ""
                        };
                        this.repetirEstrellas = function (s) {
                            repeticiones = [];
                            for (var i = 0; i < s; i++) {
                                repeticiones.push(i);
                            }
                            return repeticiones;
                        };
                        this.addOpinion = function (producto) {
                            this.opinion.fecha = Date.now();
                            //Aqui añadir el POST al backend
                            producto.reviews.push(this.opinion);
                            this.opinionEnviada = this.setOpinionEnviada(true);
                            this.opinion = {
                                estrellas: 5,
                                autor: 'Anónimo',
                                imagen: "img/user.jpg",
                                fecha: ""
                            }
                        };
                    },
                    controllerAs: 'opinionesCtrl'
                };
            });
})();
