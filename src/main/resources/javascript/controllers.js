
function GitHubController($scope, Repositories) {
    $scope.repositories = [];
    $scope.search = function() {
        Repositories.get({searchTerms: $scope.searchTerms}, function(response) {
            $scope.repositories = response['repositories'];
        });
    };
}