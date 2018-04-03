'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:DepartmentTechnicalinstitutionAddCtrl
 * @description
 * # DepartmentTechnicalinstitutionAddCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('DepartmentTechnicalinstitutionAddCtrl', ['$scope', '$controller', '$stateParams', 'departmentService', 'departmentTypeService', function($scope, $controller, $stateParams, departmentService, departmentTypeService) {
        var self = this;

        //继承RegistrationCtrl控制器
        $controller('DepartmentAddCtrl', { $scope: $scope });

        //获取所有的部门类型
        self.init = function() {
            departmentTypeService.getDepartmentTypeOfTechnicalInstitution(function(data) {
                $scope.data.user.department.departmentType = data;
            });
        };

        self.init();
    }]);
