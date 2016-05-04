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
            }).when('/error-404', {
                templateUrl: 'templates/paginas/error404/index.html'
            }).otherwise({
                redirectTo: '/error-404'
            });
        })
        .controller('ControllerAppTienda', [function() {

        }]);
})();
