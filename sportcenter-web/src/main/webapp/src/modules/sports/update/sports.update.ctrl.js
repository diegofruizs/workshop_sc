(
        function (ng) {
            var mod = ng.module("sportModule");
            mod.constant("sportsContext", "api/sports");
            mod.constant("booksContext", "api/books");
            mod.controller('sportUpdateCtrl', ['$scope', '$http', 'sportsContext', '$state', 'booksContext', '$rootScope',
                function ($scope, $http, sportsContext, $state, booksContext, $rootScope) {
                    $rootScope.edit = true;

                    $scope.data = {};

                    var idSport = $state.params.sportId;

                    $http.get(sportsContext + '/' + idSport).then(function (response) {
                        var sport = response.data;
                        $scope.data.name = sport.name;
                    });


                    $scope.createSport = function () {
                        $http.put(sportsContext + "/" + idSport, $scope.data).then(function (response) {
                            $state.go('sportsList', {sportId: response.data.id}, {reload: true});
                        });
                    }
                }]);
        }
)(window.angular);