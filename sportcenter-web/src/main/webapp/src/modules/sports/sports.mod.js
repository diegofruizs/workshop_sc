(function (ng) {

    var mod = ng.module("sportModule", ['ui.router']);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            var basePath = 'src/modules/sports/';

            $urlRouterProvider.otherwise("/sportsList");

            $stateProvider.state('sports', {
                url: '/sports',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'sports.html',
                        controller: 'sportCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('sportsList', {
                url: '/list',
                parent: 'sports',
                views: {
                    'listView': {
                        templateUrl: basePath + 'sports.list.html'
                    }
                }
            }).state('sportDetail', {
                url: '/{sportId:int}/detail',
                parent: 'sports',
                param: {sportId: null},
                views: {
                    'listView': {
                        templateUrl: basePath + 'sports.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'sports.detail.html',
                        controller: 'sportDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('sportCreate', {
                url: '/create',
                parent: 'sports',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/sports.new.html',
                        controller: 'sportNewCtrl'
                    }
                }
               
            }).state('sportUpdate', {
                url: '/update/{sportId:int}',
                parent: 'sports',
                param: {
                    sportId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/sports.new.html',
                        controller: 'sportUpdateCtrl'
                    }
                }
               
            }).state('sportDelete', {
                url: '/delete/{sportId:int}',
                parent: 'sports',
                param: {
                    sportId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/sports.delete.html',
                        controller: 'sportDeleteCtrl'
                    }
                }
               
            });
        }]);
})(window.angular);
