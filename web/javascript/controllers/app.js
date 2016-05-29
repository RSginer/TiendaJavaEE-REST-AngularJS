(function () {
    angular.module('app', ['app-productos', 'directivas-menu', 'ngRoute'])
            .run(['$rootScope', function ($rootScope) {
                    $rootScope.carrito = 0;
                }])
            .service('compartirPropiedades', function () {
                var compartida = null;
                return {
                    getCompartida: function () {
                        return compartida;
                    },
                    setCompartida: function (value) {
                        compartida = value;
                    }
                }
            })
            .config(function ($routeProvider) {
                $routeProvider.when('/', {
                    templateUrl: 'templates/paginas/inicio/index.html',
                }).when('/productos', {
                    templateUrl: 'templates/paginas/productos/index.html',
                }).when('/productos/:id', {
                    templateUrl: 'templates/paginas/productos/fichaProducto/index.html',
                    controller: function ($http, $routeParams) {
                        idProducto = $routeParams.id;
                        CtrlFichaP = this;
                        $http.get('api/productos/' + idProducto).success(function (data) {
                            CtrlFichaP.producto = data;
                        });
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
                            CtrlFichaP.producto.reviews.push(this.opinion);
                            this.opinionEnviada = this.setOpinionEnviada(true);
                            this.opinion = {imagen: "img/user.jpg"};
                        };
                    },
                    controllerAs: 'CtrlFichaP'
                }).when('/error-404', {
                    templateUrl: 'templates/paginas/error404/index.html'
                }).otherwise({redirectTo: '/error-404'});
            })

            .controller('ControllerAppTienda', [function (compartirPropiedades) {

                }]);
})();
