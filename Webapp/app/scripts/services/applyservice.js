'use strict';

/**
 * @ngdoc service
 * @name webappApp.ApplyService
 * @description
 * # ApplyService panjie
 * 申请
 */
angular.module('webappApp')
.service('ApplyService', function ($http, CommonService, WorkService) {
	var self = this;
	self.update = function(apply, callback) {
		var url = '/Apply/' + apply.id;
		$http.put(url, apply)
		.then(function success(response){
			if (callback) {callback(response.data);}
		}, function error(response){
            CommonService.httpError(response);
		});
	};

    self.showView = function(work) {
        if (work.status === WorkService.status.DONE || work.status === WorkService.status.HANDLED) {
            return true;
        } else {
            return false;
        }
    };

    self.showAudit = function(work) {
        return !self.showView(work);
    };

	return {
	    showAudit: self.showAudit,
	    showView: self.showView,
		update: self.update
	};
});
