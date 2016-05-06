(function() {
    angular.module("directivas-menu", [])
        .directive("menu", function() {
            return {
                restrict: 'E',
                templateUrl: 'templates/includes/menus/index.html',
                controller: function($location,$log) {
                    menu = this;
                    menu.pagina = function() {
                        return $location.path();
                        
                    };
                },
                controllerAs: 'menu'
            };
        });
})();
