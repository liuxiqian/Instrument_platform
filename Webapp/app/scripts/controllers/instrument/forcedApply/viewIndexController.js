'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:InstrumentForcedapplyViewindexCtrl
 * @description
 * # InstrumentForcedapplyViewindexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('InstrumentForcedapplyViewindexCtrl', function($scope,
                                                        $controller) {
        var self = this;
        angular.extend(self, $controller('InstrumentForcedApplyEditindexCtrl', { $scope: $scope }));

    });
