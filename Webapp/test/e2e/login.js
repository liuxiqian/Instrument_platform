'use strict';

/* https://github.com/angular/protractor/blob/master/docs/getting-started.md */
/*
API文档地址： http://www.protractortest.org/#/api?view=webdriver.By.partialLinkText
 */

describe('用户登录与注销', function() {
    browser.ignoreSynchronization = true;
    browser.waitForAngular();

    beforeEach(function() {
        browser.get('');    // 每次测试前，都先尝试打开根目录
    });

    it('自动跳转至用户登录界面', function() {
        expect(browser.getTitle()).toEqual('计量器具管理平台'); // 获取并断言标题
        // 获取当前的url信息，并断言必然跳转至/login
        browser.driver.getCurrentUrl().then(function(url) { 
            var absUrl = url.split('#!');
            expect(absUrl[1]).toEqual('/login');
        });
    });

    it('正确的用户名密码进行登录', function() {
        // 获取username password和登录按钮
        var username = element(by.model('user.username'));
        var password = element(by.model('user.password'));
        var login = element(by.partialButtonText('登'));

        // 自动输入用户名和密码
        username.sendKeys('admin');
        password.sendKeys('admin');

        // 前击登录
        login.click();

        // 延迟3秒等待系统完成登录
        browser.driver.sleep(3000);
        browser.waitForAngular();

        // 获取当前地址为首页的仪表台地址
        browser.driver.getCurrentUrl().then(function(url) {
            var absUrl = url.split('#!');
            expect(absUrl[1]).toEqual('/main/dashboard');
            console.log(url);
        });

        // 注销
        var logout = element(by.linkText('注销'));
        logout.click();

        // 获取当前地址为登录界面
        browser.driver.getCurrentUrl().then(function(url) {
            var absUrl = url.split('#!');
            expect(absUrl[1]).toEqual('/login');
        });

        // 打开首页并刷新，断言还会跳转到登录界面
        browser.get('#!/main/dashboard');   
        browser.refresh(); 

        browser.driver.getCurrentUrl().then(function(url) {
            var absUrl = url.split('#!');
            expect(absUrl[1]).toEqual('/login');
        });

        // 延迟，查看结果
        browser.driver.sleep(1000);
        browser.waitForAngular();

    });
});
