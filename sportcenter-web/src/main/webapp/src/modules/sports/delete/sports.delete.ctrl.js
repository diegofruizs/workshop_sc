(function (ng) {
    var mod = ng.module("sportModule");
    mod.constant("sportsContext", "api/sports");
    mod.controller('sportDeleteCtrl', ['$scope', '$http', 'sportsContext', '$state',
        function ($scope, $http, sportssContext, $state) {
            var idSport = $state.params.sportId;
            $scope.deleteSport = function () {
                $http.delete(sportssContext + '/' + idSport, {}).then(function (response) {
                    $state.go('sportsList', {sportsId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);