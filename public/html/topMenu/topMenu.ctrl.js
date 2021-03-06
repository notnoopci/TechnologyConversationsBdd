angular.module('topMenuModule', [])
    .controller('topMenuController', ['$scope', '$modal', '$location',
        function($scope, $modal, $location) {
            // TODO Test
            $scope.openStory = function() {
                $modal.open({
                    templateUrl: '/assets/html/stories.tmpl.html',
                    controller: 'storiesCtrl',
                    resolve: {
                        data: function() {
                            return {};
                        }
                    }
                });
            };
            // TODO Test
            $scope.openCompositeClass = function() {
                openCompositeClass($modal);
            };
            // TODO Test
            $scope.openRunnerSelector = function() {
                return $modal.open({
                    templateUrl: '/assets/html/runner/runnerSelector.tmpl.html',
                    controller: 'runnerSelectorCtrl',
                    resolve: {
                        data: function() {
                            return {};
                        }
                    }
                });
            };
            // TODO Test
            $scope.openRunner = function() {
                $scope.openRunnerSelector().result.then(function(data) {
                    $scope.runnerSpectorData = data;
                    openRunnerParametersModal($modal).result.then(function (data) {
                        console.log(data);
                    });
                });
            };
            $scope.getTitle = function() {
                var path = $location.path();
                if (path.indexOf(getViewStoryUrl()) === 0) {
                    return 'View Story';
                } else if (path.indexOf(getNewStoryUrl()) === 0) {
                    return 'New Story';
                } else if (path.indexOf(getNewStoryUrl()) === 0) {
                    return 'New Story';
                } else if (path.indexOf(getCompositesUrl()) === 0) {
                    return 'Composites';
                } else {
                    return '';
                }
            };
        }
    ]);
