'use strict';

/* https://github.com/angular/protractor/blob/master/docs/getting-started.md */


describe('强检器具类别管理', function() {
    browser.ignoreSynchronization = true;
    browser.waitForAngular();


    beforeEach(function() {
        browser.get(''); // 每次测试前，都先尝试打开根目录
    });


    it('正确的用户名密码进行登录', function() {
        function selectDropdownByNumber(element, index, milliseconds) {
            element.findElements(by.tagName('option'))
              .then(function(options) {
                options[index].click();
              });
            if (typeof milliseconds !== 'undefined') {
              browser.sleep(milliseconds);
           }
        };

        var EC = protractor.ExpectedConditions; // 期望表达式

        console.log('获取username password和登录按钮');
        var username = element(by.model('user.username'));
        var password = element(by.model('user.password'));
        var login = element(by.partialButtonText('登'));

        console.log('自动输入用户名和密码');
        username.sendKeys('admin');
        password.sendKeys('admin');

        console.log('前击登录');
        login.click();

        console.log('延迟3秒等待系统完成登录');
        browser.driver.sleep(3000);
        browser.waitForAngular();

        console.log('点击系统设置');
        var systemMenu = element(by.partialLinkText('系统设置'));
        systemMenu.click();

        console.log('点击强检器具类别管理');
        var instrumentTypeMenu = element(by.partialLinkText('强检器具类别管理'));
        browser.wait(EC.elementToBeClickable(instrumentTypeMenu, 5000));
        instrumentTypeMenu.click();

        console.log('延迟1S');
        browser.driver.sleep(3000);
        browser.waitForAngular();

        console.log('选择学科类别');
        var discipline = element(by.model('discipline'));
        discipline.evaluate("discipline.id = 1;");

        browser.driver.sleep(500);
        browser.waitForAngular();

        var addButton = element(by.partialButtonText('新增'));
        addButton.click();

        browser.driver.sleep(3000);
        browser.waitForAngular();

        console.log('如果只有请选择一个元素，则添加一个一级类别');
        var firstLevelName = '测试一级类别';
        element(by.model('data.instrumentFirstLevelType')).element(by.repeater('list in lists')).evaluate('lists').then(function(posts) {
            if (posts.length < 2) {
                console.log('添加一级类别');
                var addFirstLevelButton = element(by.buttonText('+'));
                addFirstLevelButton.click();

                var name = element(by.model('name'));
                name.sendKeys(firstLevelName);
                var pinyin = element(by.model('pinyin'));
                pinyin.sendKeys('ceshiyijileibie');

                var submitButton = element(by.buttonText('确定'));
                submitButton.click();
                browser.driver.sleep(1500);
                browser.waitForAngular();
            }
        });

        // var selectIndex = 0;
        // element(by.model('data.instrumentFirstLevelType')).element(by.repeater('list in lists')).evaluate('lists').then(function(lists){
        //     lists.forEach(function(value, index) {
        //         console.log(value.name);
        //         if (value.name === firstLevelName) {
        //             selectIndex = index;
        //         }
        //     })
        // });

        console.log('点击一级学级类别');
        var firstLevel = element(by.model('data.instrumentFirstLevelType'))
            .element(by.model('ngModel'));
        firstLevel.click();

        console.log('选择第一个选项');
        var instrumentType = element(by.model('data.instrumentFirstLevelType'))
            .element(by.repeater('list in lists').row(1));
        browser.wait(EC.elementToBeClickable(instrumentType), 5000);
        instrumentType.click();
       
        element(by.model('data.name')).sendKeys('测试二级分类名称');
        element(by.model('data.pinyin')).sendKeys('erjifenleimingcheng');

        browser.driver.sleep(3000);
        browser.waitForAngular();

        console.log('注销');
        var logout = element(by.linkText('注销'));
        logout.click();

        console.log('延迟，查看结果');
        browser.driver.sleep(1000);
        browser.waitForAngular();

    });
});
