exports.config = {
    // 运行所有的脚本的最长时间
    allScriptsTimeout: 99999,
    // The address of a running selenium server.前面，我们使用start命令启动的服务地址
    seleniumAddress: 'http://localhost:4444/wd/hub',

    // Capabilities to be passed to the webdriver instance. 测试兼容性的浏览器设置
    capabilities: {
        'browserName': 'chrome',
        'chromeOptions': {'args': ['--window-size=1690,1000'] }
    },

    // 项目地址
    baseUrl: 'http://localhost:8080/',

    framework: 'jasmine',

    // Spec patterns are relative to the current working directly when
    // protractor is called.
    // 测试文件所在位置（使用的是表达式）
    specs: ['test/e2e/instrumentType.js'],

    // Options to be passed to Jasmine-node.
    // 其它选项
    jasmineNodeOpts: {
        showColors: true,
        defaultTimeoutInterval: 30000,
        isVerbose: true,
        includeStackTrace: true
    },

    onPrepare: function () {
      // Your other stuff.
      // 引入ui-sref, 参考：https://www.npmjs.com/package/protractor-linkuisref-locator
      require('protractor-linkuisref-locator')(protractor);
    }

};

/**
* Usage: selectDropdownByNumber ( element, index)
* element : select element
* index : index in the dropdown, 1 base.
*/
exports.selectDropdownByNumber = function (element, index, milliseconds) {
    element.findElements(by.tagName('option'))
        .then(function (options) {
            options[index].click();
        });
    if (typeof milliseconds != 'undefined') {
        browser.sleep(milliseconds);
    }
};


/**
* Usage: selectDropdownByText (selector, item)
* selector : select element
* item : option(s) in the dropdown.
*/
exports.selectDropdownByText = function selectOption(element, item, milliseconds) {
    var desiredOption;
    element.findElements(by.tagName('option'))
    .then(function findMatchingOption(options) {
        options.some(function (option) {
            option.getText().then(function doesOptionMatch(text) {
                if (text.indexOf(item) != -1) {
                    desiredOption = option;
                    return true;
                }
            });
        });
    })
    .then(function clickOption() {
        if (desiredOption) {
            desiredOption.click();
        }
    });
    if (typeof milliseconds != 'undefined') {
        browser.sleep(milliseconds);
    }
};

/**
* Usage: selectRandomDropdownReturnText ( element, milliseconds)
* element : select random element
* index : wait time to select value for drop down.
*/
exports.selectRandomDropdownReturnText = function (element, milliseconds) {
    return element.findElements(by.tagName('option')).then(function (options) {
        var randomNumber = Math.floor((Math.random() * options.length
        ));
        options[randomNumber].click();
        return options[randomNumber].getText().then(function (text) {
            return text;
        })
    })
    if (typeof milliseconds != 'undefined') {
        browser.sleep(milliseconds);
    }
};
