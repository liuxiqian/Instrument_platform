'use strict';

/**
 * @ngdoc factory
 * @name webappApp.superCache
 * @description 缓存模块
 * # superCache
 * Factory in the webappApp.
 */
angular.module('webappApp')
    .factory('superCache', ['$cacheFactory', function($cacheFactory) {
        return $cacheFactory('super-cache');
    }]);
