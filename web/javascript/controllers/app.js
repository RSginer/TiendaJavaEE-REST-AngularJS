(function() {
    angular.module('app', ['app-productos', 'directivas-menu', 'ngRoute'])
        .config(function($routeProvider) {
            $routeProvider.when('/', {
                templateUrl: 'templates/paginas/inicio/index.html',
                controller: function() {
                    this.pagina = 1;
                },
                controllerAs: 'inicio'

            }).when('/productos', {
                templateUrl: 'templates/paginas/productos/index.html',
                controller: function() {
                    this.pagina = 2;
                },
                controllerAs: 'producto'
            }).when('/productos/:id', {
                templateUrl:'templates/paginas/productos/fichaProducto/index.html',
                controller:function($http,$routeParams){
                    fichaProducto = this;
                        $http.get('api/productos/' + $routeParams.id).success(function(data) {
                        fichaProducto.p = data;
                        });
                },
                controllerAs:'CtrlFichaP'
            }).when('/error-404', {
                templateUrl: 'templates/paginas/error404/index.html'
            });
        })
        .controller('ControllerAppTienda', [function() {

        }]);
})();
