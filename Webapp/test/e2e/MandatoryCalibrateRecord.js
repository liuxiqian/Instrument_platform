'use strict';

/* https://github.com/angular/protractor/blob/master/docs/getting-started.md */


describe('强检器具备案管理', function() {
    browser.ignoreSynchronization = true;
    browser.waitForAngular();


    beforeEach(function() {
        browser.get('');    // 每次测试前，都先尝试打开根目录
    });


    it('正确的用户名密码进行登录', function() {
        var EC = protractor.ExpectedConditions;

        // 获取username password和登录按钮
        var username = element(by.model('user.username'));
        var password = element(by.model('user.password'));
        var login = element(by.partialButtonText('登'));

        // 自动输入用户名和密码
        username.sendKeys('user1');
        password.sendKeys('user1');

        // 前击登录
        login.click();

        // 延迟3秒等待系统完成登录
        browser.driver.sleep(3000);
        browser.waitForAngular();

        // 注销
        var MandatoryCalibrateRecordMenu = element(by.partialLinkText('强检器具备案管理'));
        MandatoryCalibrateRecordMenu.click();

        // 延迟3秒
        browser.driver.sleep(3000);
        browser.waitForAngular();

        var addButton = element(by.partialLinkText('新增'));
        addButton.click();

        // 延迟3秒
        browser.driver.sleep(1000);
        browser.waitForAngular();
        
        // 断言跳转后的URL
        browser.driver.getCurrentUrl().then(function(url) {
            var absUrl = url.split('#!');

            expect(absUrl[1]).toContain('/mandatory/IntegratedAdd');
        });


        // 延迟，查看结果
        browser.driver.sleep(1000);
        browser.waitForAngular();

    });
});
