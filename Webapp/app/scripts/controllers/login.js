'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:LoginCtrl
 * @description
 * # LoginCtrl 用户登录
 * Controller of the webappApp
 */
angular.module('webappApp')
.controller('LoginCtrl', ['$scope', '$state', 'UserServer', '$location', 'config', 'sweetAlert', 'WebAppMenuService', '$timeout',
    function ($scope, $state, UserServer, $location, config, sweetAlert, WebAppMenuService, $timeout) {
	var self = this;
	UserServer.init();
	self.login = function (user) {
		UserServer.login(user, function (status) {
			if (status === 401) {
				self.setMessage('对不起', '您的用户名或密码输入有误或用' +
					'户状态不正常，请重新输入。');
				$scope.loginForm.$submitted = false;
				$state.reload();
			} else if (status === 200) {
				// 登录成功，先清空缓存，然后跳转.自动跳转
				$location.path(config.mainPath);
			} else {
				self.setMessage('对不起', '系统发生未知错误，请稍后重试，或联系您的管理员。');
                $state.reload();
			}
		});
	};

	// 调用sweetAlert进行用户名密码错误的提示
	self.setMessage = function (title, message) {
		sweetAlert.swal({
			title: title,
			text: message
		});
	};

	self.cloudParentWidth = $('.cloud').parent().width();
	self.cloudLeft = Math.floor((Math.random() * self.cloudParentWidth) + 1);
	self.cloudFly = function () {
		$timeout(function () {
			self.cloudLeft++;
			if (self.cloudLeft > self.cloudParentWidth + 150) {
				self.cloudLeft = -200;
			}
			// 设置云朵的左边距，视觉上达到飘的效果
			$scope.cloudStyle = {left: self.cloudLeft + 'px'};
			self.cloudFly();
		}, 20);
	};
	self.cloudFly();

	// 当前窗口高度
	self.windowHeight = $(window).height() - 247 >= 336 ? $(window).height() - 247 : 336;
	$('.loginbox').height(self.windowHeight);


	//跳转到注册界面
	$scope.registration = function () {
		$location.path('/registration');
	};

	$scope.user = {username: '', password: ''};
	$scope.login = function () {
		self.login($scope.user);
	};
	$scope.message = '';
	$scope.loginForm = {};
}]);
