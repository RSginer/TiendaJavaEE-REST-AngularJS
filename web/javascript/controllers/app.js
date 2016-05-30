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
                    controller: function ($http, $routeParams,$scope) {
                        idProducto = $routeParams.id;
                        CtrlFichaP = this;
                        $http.get('api/productos/' + idProducto).success(function (data) {
                        $scope.CtrlFichaP.producto = data;
                        });
                    },
                    controllerAs: 'CtrlFichaP'
                }).when('/error-404', {
                    templateUrl: 'templates/paginas/error404/index.html'
                }).otherwise({redirectTo: '/error-404'});
            })

            .controller('ControllerAppTienda', [function () {

                }]);
})();
