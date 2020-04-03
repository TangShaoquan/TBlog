/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost

 Source Schema         : tblog

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 03/04/2020 10:27:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `article_id` int(11) NOT NULL AUTO_INCREMENT,
  `article_user_id` int(11) UNSIGNED NULL DEFAULT NULL,
  `article_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `article_content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `article_view_count` int(11) NULL DEFAULT 0,
  `article_comment_count` int(11) NULL DEFAULT 0,
  `article_like_count` int(11) NULL DEFAULT 0,
  `article_is_comment` int(1) UNSIGNED NULL DEFAULT NULL,
  `article_status` int(1) UNSIGNED NULL DEFAULT 1,
  `article_order` int(11) UNSIGNED NULL DEFAULT NULL,
  `article_update_time` datetime(0) NULL DEFAULT NULL,
  `article_create_time` datetime(0) NULL DEFAULT NULL,
  `article_summary` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`article_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 82 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (81, 1, '虚函数详解', '<h2 id=\"h2-u865Au51FDu6570\"><a name=\"虚函数\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>虚函数</h2><p>虚函数在C++中的基类由关键字vritual声明，允许在一个或多个派生类中重新定义。虚函数通过指向派生类中的基类指针或引用来实现多态性，多态性就是将接口与实现进行分离，能且仅能通过指针或引用可以访问派生类中的同名覆盖函数。</p>\r\n<h2 id=\"h2-u591Au6001u662Fu4EC0u4E48\"><a name=\"多态是什么\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>多态是什么</h2><p>联编：联编就是将模块或者函数合并在一起生成可执行代码的处理过程，同时对每个模块或者函数调用分配内存地址，并且对外部访问也分配正确的内存地址，它是计算机程序彼此关联的过程。<br>多态也称为动态联编或迟后联编，因为到底调用哪一个函数，在编译时不能确定，而要推迟到运行时确定。也就是说，要等到程序运行时，确定了指针所指向的对象的类型时，才能够确定。<br>C++ 多态意味着调用成员函数时，会根据调用函数的对象的类型来执行不同的函数。<br>实现多态的三个条件：</p>\r\n<ol>\r\n<li>存在继承关系</li><li>重写父类的virtual function</li><li>子类以父类的指针或者是引用的身份出现</li></ol>\r\n<h2 id=\"h2-u4E3Au4EC0u4E48u9700u8981u865Au51FDu6570\"><a name=\"为什么需要虚函数\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>为什么需要虚函数</h2><p>多态性是面向对象设计语言的基本特征。仅仅是将数据和函数捆绑在一起，进行类的封装，使用一些简单的继承，还不能算是真正应用了面向对象的设计思想。</p>\r\n<h2 id=\"h2-u5B9Eu4F8B\"><a name=\"实例\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>实例</h2><pre><code>Class Base{\r\npublic:\r\n    void print(){\r\n</code><p><code>        cout&lt;&lt;\"This Class is Base Class\"&lt;</code></p>\r\n<p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\"><endl;<o:p></endl;<o:p></span></p>\r\n\r\n<p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">&nbsp;&nbsp;&nbsp; }<o:p></o:p></span></p>\r\n\r\n<p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">}<o:p></o:p></span></p>\r\n\r\n<p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">Class A : public Base{<o:p></o:p></span></p>\r\n\r\n<p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">&nbsp;&nbsp;&nbsp; void print(){<o:p></o:p></span></p>\r\n\r\n<p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; cout&lt;&lt;\"This Class\r\nis A Class\"&lt;<endl;< span=\"\"></endl;<></span></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">&nbsp;&nbsp;&nbsp; }</span></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">}</span></p>\r\n<p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">int main(){</span></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">&nbsp;&nbsp;&nbsp; Base ba;</span></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">&nbsp;&nbsp;&nbsp; A b;</span></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">&nbsp;&nbsp;&nbsp; ba.print();</span></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">&nbsp;&nbsp;&nbsp; b.print();</span></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">}</span></p></pre><p><code>上述代码输出</code></p><code>\r\n<pre><code>This Class is Base Class\r\n\r\nThis Class is A Class\r\n</code></pre><p>此实例并没有实现多态，只是单纯的调用而已。<br>只有子类以父类的引用或函数的指针形式调用时才能出现多态。<br>若以指针方式调用而不以virtual修饰父类函数：</p>\r\n<pre><code>Class Base{\r\npublic:\r\n    void print(){\r\n        cout&lt;&lt;\"This Class is Base Class\"&lt;<endl; }=\"\" class=\"\" a=\"\" :=\"\" public=\"\" base{=\"\" void=\"\" print(){=\"\" cout<<\"this=\"\" is=\"\" class\"<<endl;=\"\" int=\"\" main(){=\"\" base=\"\" ba;=\"\" b;=\"\" base*=\"\" ptr_base=\"&amp;ba;\" ptr_b=\"&amp;b;\" ptr_base-=\"\">print();\r\n    ptr_b-&gt;print();\r\n</endl;></code><p><code><endl; }=\"\" class=\"\" a=\"\" :=\"\" public=\"\" base{=\"\" void=\"\" print(){=\"\" cout<<\"this=\"\" is=\"\" class\"<<endl;=\"\" int=\"\" main(){=\"\" base=\"\" ba;=\"\" b;=\"\" base*=\"\" ptr_base=\"&amp;ba;\" ptr_b=\"&amp;b;\" ptr_base-=\"\">}</endl;></code></p><p><code><endl; }=\"\" class=\"\" a=\"\" :=\"\" public=\"\" base{=\"\" void=\"\" print(){=\"\" cout<<\"this=\"\" is=\"\" class\"<<endl;=\"\" int=\"\" main(){=\"\" base=\"\" ba;=\"\" b;=\"\" base*=\"\" ptr_base=\"&amp;ba;\" ptr_b=\"&amp;b;\" ptr_base-=\"\">\r\n\r\n</endl;></code></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\"><o:p>&nbsp;</o:p></span></p><p><code><endl; }=\"\" class=\"\" a=\"\" :=\"\" public=\"\" base{=\"\" void=\"\" print(){=\"\" cout<<\"this=\"\" is=\"\" class\"<<endl;=\"\" int=\"\" main(){=\"\" base=\"\" ba;=\"\" b;=\"\" base*=\"\" ptr_base=\"&amp;ba;\" ptr_b=\"&amp;b;\" ptr_base-=\"\">\r\n\r\n</endl;></code></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">Class A : public Base{<o:p></o:p></span></p><p><code><endl; }=\"\" class=\"\" a=\"\" :=\"\" public=\"\" base{=\"\" void=\"\" print(){=\"\" cout<<\"this=\"\" is=\"\" class\"<<endl;=\"\" int=\"\" main(){=\"\" base=\"\" ba;=\"\" b;=\"\" base*=\"\" ptr_base=\"&amp;ba;\" ptr_b=\"&amp;b;\" ptr_base-=\"\">\r\n\r\n</endl;></code></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">&nbsp;&nbsp;&nbsp; void print(){<o:p></o:p></span></p><p><code><endl; }=\"\" class=\"\" a=\"\" :=\"\" public=\"\" base{=\"\" void=\"\" print(){=\"\" cout<<\"this=\"\" is=\"\" class\"<<endl;=\"\" int=\"\" main(){=\"\" base=\"\" ba;=\"\" b;=\"\" base*=\"\" ptr_base=\"&amp;ba;\" ptr_b=\"&amp;b;\" ptr_base-=\"\">\r\n\r\n</endl;></code></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; cout&lt;&lt;\"This Class\r\nis A Class\"&lt;<endl;<o:p></endl;<o:p></span></p><p><code><endl; }=\"\" class=\"\" a=\"\" :=\"\" public=\"\" base{=\"\" void=\"\" print(){=\"\" cout<<\"this=\"\" is=\"\" class\"<<endl;=\"\" int=\"\" main(){=\"\" base=\"\" ba;=\"\" b;=\"\" base*=\"\" ptr_base=\"&amp;ba;\" ptr_b=\"&amp;b;\" ptr_base-=\"\">\r\n\r\n</endl;></code></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">&nbsp;&nbsp;&nbsp; }<o:p></o:p></span></p><p><code><endl; }=\"\" class=\"\" a=\"\" :=\"\" public=\"\" base{=\"\" void=\"\" print(){=\"\" cout<<\"this=\"\" is=\"\" class\"<<endl;=\"\" int=\"\" main(){=\"\" base=\"\" ba;=\"\" b;=\"\" base*=\"\" ptr_base=\"&amp;ba;\" ptr_b=\"&amp;b;\" ptr_base-=\"\">\r\n\r\n</endl;></code></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">}<o:p></o:p></span></p><p><code><endl; }=\"\" class=\"\" a=\"\" :=\"\" public=\"\" base{=\"\" void=\"\" print(){=\"\" cout<<\"this=\"\" is=\"\" class\"<<endl;=\"\" int=\"\" main(){=\"\" base=\"\" ba;=\"\" b;=\"\" base*=\"\" ptr_base=\"&amp;ba;\" ptr_b=\"&amp;b;\" ptr_base-=\"\">\r\n\r\n</endl;></code></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\"><o:p>&nbsp;</o:p></span></p><p><code><endl; }=\"\" class=\"\" a=\"\" :=\"\" public=\"\" base{=\"\" void=\"\" print(){=\"\" cout<<\"this=\"\" is=\"\" class\"<<endl;=\"\" int=\"\" main(){=\"\" base=\"\" ba;=\"\" b;=\"\" base*=\"\" ptr_base=\"&amp;ba;\" ptr_b=\"&amp;b;\" ptr_base-=\"\">\r\n\r\n</endl;></code></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">int main(){<o:p></o:p></span></p><p><code><endl; }=\"\" class=\"\" a=\"\" :=\"\" public=\"\" base{=\"\" void=\"\" print(){=\"\" cout<<\"this=\"\" is=\"\" class\"<<endl;=\"\" int=\"\" main(){=\"\" base=\"\" ba;=\"\" b;=\"\" base*=\"\" ptr_base=\"&amp;ba;\" ptr_b=\"&amp;b;\" ptr_base-=\"\">\r\n\r\n</endl;></code></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">&nbsp;&nbsp;&nbsp; Base ba;<o:p></o:p></span></p><p><code><endl; }=\"\" class=\"\" a=\"\" :=\"\" public=\"\" base{=\"\" void=\"\" print(){=\"\" cout<<\"this=\"\" is=\"\" class\"<<endl;=\"\" int=\"\" main(){=\"\" base=\"\" ba;=\"\" b;=\"\" base*=\"\" ptr_base=\"&amp;ba;\" ptr_b=\"&amp;b;\" ptr_base-=\"\">\r\n\r\n</endl;></code></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">&nbsp;&nbsp;&nbsp; A b;<o:p></o:p></span></p><p><code><endl; }=\"\" class=\"\" a=\"\" :=\"\" public=\"\" base{=\"\" void=\"\" print(){=\"\" cout<<\"this=\"\" is=\"\" class\"<<endl;=\"\" int=\"\" main(){=\"\" base=\"\" ba;=\"\" b;=\"\" base*=\"\" ptr_base=\"&amp;ba;\" ptr_b=\"&amp;b;\" ptr_base-=\"\">\r\n\r\n</endl;></code></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">&nbsp;&nbsp;&nbsp; Base* ptr_base = &amp;ba;<o:p></o:p></span></p><p><code><endl; }=\"\" class=\"\" a=\"\" :=\"\" public=\"\" base{=\"\" void=\"\" print(){=\"\" cout<<\"this=\"\" is=\"\" class\"<<endl;=\"\" int=\"\" main(){=\"\" base=\"\" ba;=\"\" b;=\"\" base*=\"\" ptr_base=\"&amp;ba;\" ptr_b=\"&amp;b;\" ptr_base-=\"\">\r\n\r\n</endl;></code></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">&nbsp;&nbsp;&nbsp; Base* ptr_b = &amp;b;<o:p></o:p></span></p><p><code><endl; }=\"\" class=\"\" a=\"\" :=\"\" public=\"\" base{=\"\" void=\"\" print(){=\"\" cout<<\"this=\"\" is=\"\" class\"<<endl;=\"\" int=\"\" main(){=\"\" base=\"\" ba;=\"\" b;=\"\" base*=\"\" ptr_base=\"&amp;ba;\" ptr_b=\"&amp;b;\" ptr_base-=\"\">\r\n\r\n</endl;></code></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">&nbsp;&nbsp;&nbsp; ptr_base-&gt;print();<o:p></o:p></span></p><p><code><endl; }=\"\" class=\"\" a=\"\" :=\"\" public=\"\" base{=\"\" void=\"\" print(){=\"\" cout<<\"this=\"\" is=\"\" class\"<<endl;=\"\" int=\"\" main(){=\"\" base=\"\" ba;=\"\" b;=\"\" base*=\"\" ptr_base=\"&amp;ba;\" ptr_b=\"&amp;b;\" ptr_base-=\"\">\r\n\r\n</endl;></code></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">&nbsp;&nbsp;&nbsp; ptr_b-&gt;print();<o:p></o:p></span></p><p><code><endl; }=\"\" class=\"\" a=\"\" :=\"\" public=\"\" base{=\"\" void=\"\" print(){=\"\" cout<<\"this=\"\" is=\"\" class\"<<endl;=\"\" int=\"\" main(){=\"\" base=\"\" ba;=\"\" b;=\"\" base*=\"\" ptr_base=\"&amp;ba;\" ptr_b=\"&amp;b;\" ptr_base-=\"\">\r\n\r\n</endl;></code></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">}</span><span lang=\"EN-US\"><o:p></o:p></span></p><p><code><endl; }=\"\" class=\"\" a=\"\" :=\"\" public=\"\" base{=\"\" void=\"\" print(){=\"\" cout<<\"this=\"\" is=\"\" class\"<<endl;=\"\" int=\"\" main(){=\"\" base=\"\" ba;=\"\" b;=\"\" base*=\"\" ptr_base=\"&amp;ba;\" ptr_b=\"&amp;b;\" ptr_base-=\"\">\r\n\r\n</endl;></code></p></pre><p>输出的是：</p>\r\n<pre><code>This Class is Base Class\r\nThis Class is Base Class\r\n</code></pre><p>这是因为两个指针的类型都是Base指针，调用的当然是Base的成员函数。用virtual修饰父类的函数就可以实现用父类指针调用。</p>\r\n<pre><code>Class Base{\r\npublic:\r\n   virtual void print(){\r\n        cout&lt;&lt;\"This Class is Base Class\"&lt;<endl; }=\"\" class=\"\" a=\"\" :=\"\" public=\"\" base{=\"\" void=\"\" print(){=\"\" cout<<\"this=\"\" is=\"\" class\"<<endl;=\"\" int=\"\" main(){=\"\" base=\"\" ba;=\"\" b;=\"\" base*=\"\" ptr_base=\"&amp;ba;\" ptr_b=\"&amp;b;\" ptr_base-=\"\">print();\r\n    ptr_b-&gt;print();\r\n</endl;></code><p><code><endl; }=\"\" class=\"\" a=\"\" :=\"\" public=\"\" base{=\"\" void=\"\" print(){=\"\" cout<<\"this=\"\" is=\"\" class\"<<endl;=\"\" int=\"\" main(){=\"\" base=\"\" ba;=\"\" b;=\"\" base*=\"\" ptr_base=\"&amp;ba;\" ptr_b=\"&amp;b;\" ptr_base-=\"\">}</endl;></code></p><p><code><endl; }=\"\" class=\"\" a=\"\" :=\"\" public=\"\" base{=\"\" void=\"\" print(){=\"\" cout<<\"this=\"\" is=\"\" class\"<<endl;=\"\" int=\"\" main(){=\"\" base=\"\" ba;=\"\" b;=\"\" base*=\"\" ptr_base=\"&amp;ba;\" ptr_b=\"&amp;b;\" ptr_base-=\"\">\r\n</endl;></code></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">Class A : public Base{</span></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">&nbsp;&nbsp;&nbsp; void print(){</span></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; cout&lt;&lt;\"This Class\r\nis A Class\"&lt;<endl;< span=\"\"></endl;<></span></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">&nbsp;&nbsp;&nbsp; }<o:p></o:p></span></p><p><code><endl; }=\"\" class=\"\" a=\"\" :=\"\" public=\"\" base{=\"\" void=\"\" print(){=\"\" cout<<\"this=\"\" is=\"\" class\"<<endl;=\"\" int=\"\" main(){=\"\" base=\"\" ba;=\"\" b;=\"\" base*=\"\" ptr_base=\"&amp;ba;\" ptr_b=\"&amp;b;\" ptr_base-=\"\">\r\n\r\n</endl;></code></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">}</span></p><p><code><endl; }=\"\" class=\"\" a=\"\" :=\"\" public=\"\" base{=\"\" void=\"\" print(){=\"\" cout<<\"this=\"\" is=\"\" class\"<<endl;=\"\" int=\"\" main(){=\"\" base=\"\" ba;=\"\" b;=\"\" base*=\"\" ptr_base=\"&amp;ba;\" ptr_b=\"&amp;b;\" ptr_base-=\"\">\r\n</endl;></code></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">int main(){</span></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">&nbsp;&nbsp;&nbsp; Base ba;</span></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">&nbsp;&nbsp;&nbsp; A b;</span></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">&nbsp;&nbsp;&nbsp; Base* ptr_base = &amp;ba;</span></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">&nbsp;&nbsp;&nbsp; Base* ptr_b = &amp;b;</span></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">&nbsp;&nbsp;&nbsp; ptr_base-&gt;print();</span></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">&nbsp;&nbsp;&nbsp; ptr_b-&gt;print();</span></p><p><code><endl; }=\"\" class=\"\" a=\"\" :=\"\" public=\"\" base{=\"\" void=\"\" print(){=\"\" cout<<\"this=\"\" is=\"\" class\"<<endl;=\"\" int=\"\" main(){=\"\" base=\"\" ba;=\"\" b;=\"\" base*=\"\" ptr_base=\"&amp;ba;\" ptr_b=\"&amp;b;\" ptr_base-=\"\">\r\n</endl;></code></p><p class=\"MsoNormal\" align=\"left\"><span lang=\"EN-US\">}<o:p></o:p></span></p><p><code><endl; }=\"\" class=\"\" a=\"\" :=\"\" public=\"\" base{=\"\" void=\"\" print(){=\"\" cout<<\"this=\"\" is=\"\" class\"<<endl;=\"\" int=\"\" main(){=\"\" base=\"\" ba;=\"\" b;=\"\" base*=\"\" ptr_base=\"&amp;ba;\" ptr_b=\"&amp;b;\" ptr_base-=\"\">\r\n</endl;></code></p><code><endl; }=\"\" class=\"\" a=\"\" :=\"\" public=\"\" base{=\"\" void=\"\" print(){=\"\" cout<<\"this=\"\" is=\"\" class\"<<endl;=\"\" int=\"\" main(){=\"\" base=\"\" ba;=\"\" b;=\"\" base*=\"\" ptr_base=\"&amp;ba;\" ptr_b=\"&amp;b;\" ptr_base-=\"\">输出\r\n    This Class is Base Class\r\n    This Class is A Class\r\n</endl;></code></pre><h2 id=\"h2-u865Au51FDu6570u5982u4F55u5B9Eu73B0u591Au6001\"><a name=\"虚函数如何实现多态\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>虚函数如何实现多态</h2><p>虚函数通过虚函数表指针(vptr)，和虚函数表(vftable)实现<br>在有虚函数存在的类中，编译器会在类对象前插入一张虚函数表，类创建的每个对象都有它的虚函数指针，指向类的虚函数表</p>\r\n<p>对于虚函数表</p>\r\n<ul>\r\n<li><p>&nbsp;继承的体系越复杂，子类的体积越大。</p>\r\n</li><li><p>&nbsp;子类中普通成员顺序按照继承的先后顺序来的。</p>\r\n</li><li><p>&nbsp;多重继承，子类中含有多个vptr，分别指向不同的vftable。</p>\r\n</li><li><p>&nbsp;vftable中的虚函数地址和在类中声明的顺序一致。</p>\r\n</li><li><p>&nbsp;如果子类override父类中虚函数，那么子类vftable中就会替换原来父类的虚函数。</p>\r\n</li><li><p>&nbsp;如果子类自己含有额外的虚函数，则会附加到第一个vftable中。</p>\r\n</li><li><p>&nbsp;vftable中的最后一个值可能为0x0，有时候并不是为0，上图中红色字部分。</p>\r\n</li><li><p>&nbsp;子类有vftable，同时父类也有一份vftable，两个vftable没有关联。每个实例化子类都共享一个vftable，同样父类所有实例化对象也共享一份vftable，非常类似类的静态变量。vftable里的内容在编译期间初始化。</p>\r\n</li></ul>\r\n<h2 id=\"h2-u7EAFu865Au51FDu6570\"><a name=\"纯虚函数\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>纯虚函数</h2><p>在虚函数原型的后面加上=0（virtual void func()= 0），同时这个函数是没有实现的。<br>有纯虚函数的类表示这是一个抽象类，抽象类不能实例化。关于抽象类不能实例化。比如说：动物，老虎，狮子，人都是动物。但 说动物没人能理解你说的动物到底指的是什么东西。<br>由纯虚函数的引出了抽象类，抽象类的出现是为了解决是为了被继承的，它为子类实例化提供蓝图。在相关的组织继承层次中，它来提供一个公共的根。其他相关子类都是这里衍生出来。<br>它与接口的区别是什么？<br>接口是对动作的抽象，抽象类是对根源的抽象。比如说人，有五官，有其他属性。但是吃这个动作应该定义为接口更合适。因为其他动物也有吃的动作。</p>\r\n<h2 id=\"h2-u865Au51FDu6570u7684u7F3Au70B9\"><a name=\"虚函数的缺点\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>虚函数的缺点</h2><ol>\r\n<li>&nbsp;间接寻址造成的效率慢。</li><li>&nbsp;继承关系带来的强耦合关系，父类动子类可能地动山摇，对象关系复杂度上升。</li><li>&nbsp;体积的增加，尤其是多继承时体现的更明显，引入额外的空间复杂。</li></ol>\r\n</code>', 12, 1, 0, 1, 1, 1, '2020-04-02 16:11:40', '2020-04-02 08:07:10', '虚函数在C++中的基类由关键字vritual声明，允许在一个或多个派生类中重新定义。虚函数通过指向派生类中的基类指针或引用来实现多态性，多态性就是将接口与实现进行分离，能且仅能通过指针或引用可以访问派生类中的同名覆盖函数。');

-- ----------------------------
-- Table structure for article_category_ref
-- ----------------------------
DROP TABLE IF EXISTS `article_category_ref`;
CREATE TABLE `article_category_ref`  (
  `article_id` int(11) NULL DEFAULT NULL,
  `category_id` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_category_ref
-- ----------------------------
INSERT INTO `article_category_ref` VALUES (18, NULL);
INSERT INTO `article_category_ref` VALUES (25, 10);
INSERT INTO `article_category_ref` VALUES (25, 12);
INSERT INTO `article_category_ref` VALUES (24, 10);
INSERT INTO `article_category_ref` VALUES (24, 13);
INSERT INTO `article_category_ref` VALUES (26, 15);
INSERT INTO `article_category_ref` VALUES (26, 100000004);
INSERT INTO `article_category_ref` VALUES (14, 1);
INSERT INTO `article_category_ref` VALUES (14, 9);
INSERT INTO `article_category_ref` VALUES (17, 1);
INSERT INTO `article_category_ref` VALUES (17, 2);
INSERT INTO `article_category_ref` VALUES (16, 1);
INSERT INTO `article_category_ref` VALUES (16, 2);
INSERT INTO `article_category_ref` VALUES (15, 1);
INSERT INTO `article_category_ref` VALUES (15, 8);
INSERT INTO `article_category_ref` VALUES (13, 10);
INSERT INTO `article_category_ref` VALUES (13, 13);
INSERT INTO `article_category_ref` VALUES (12, 1);
INSERT INTO `article_category_ref` VALUES (12, 2);
INSERT INTO `article_category_ref` VALUES (11, 1);
INSERT INTO `article_category_ref` VALUES (11, 2);
INSERT INTO `article_category_ref` VALUES (4, 10);
INSERT INTO `article_category_ref` VALUES (4, 14);
INSERT INTO `article_category_ref` VALUES (5, 1);
INSERT INTO `article_category_ref` VALUES (5, 8);
INSERT INTO `article_category_ref` VALUES (1, 1);
INSERT INTO `article_category_ref` VALUES (1, 9);
INSERT INTO `article_category_ref` VALUES (2, 1);
INSERT INTO `article_category_ref` VALUES (2, 9);
INSERT INTO `article_category_ref` VALUES (6, 1);
INSERT INTO `article_category_ref` VALUES (6, 9);
INSERT INTO `article_category_ref` VALUES (10, 1);
INSERT INTO `article_category_ref` VALUES (10, 2);
INSERT INTO `article_category_ref` VALUES (9, 1);
INSERT INTO `article_category_ref` VALUES (9, 9);
INSERT INTO `article_category_ref` VALUES (8, 1);
INSERT INTO `article_category_ref` VALUES (8, 9);
INSERT INTO `article_category_ref` VALUES (3, 1);
INSERT INTO `article_category_ref` VALUES (3, 8);
INSERT INTO `article_category_ref` VALUES (7, 1);
INSERT INTO `article_category_ref` VALUES (7, 9);
INSERT INTO `article_category_ref` VALUES (23, 1);
INSERT INTO `article_category_ref` VALUES (23, 7);
INSERT INTO `article_category_ref` VALUES (34, 10);
INSERT INTO `article_category_ref` VALUES (34, 13);
INSERT INTO `article_category_ref` VALUES (33, 10);
INSERT INTO `article_category_ref` VALUES (33, 13);
INSERT INTO `article_category_ref` VALUES (32, 15);
INSERT INTO `article_category_ref` VALUES (32, 100000006);
INSERT INTO `article_category_ref` VALUES (31, 15);
INSERT INTO `article_category_ref` VALUES (31, 16);
INSERT INTO `article_category_ref` VALUES (30, 10);
INSERT INTO `article_category_ref` VALUES (30, 13);
INSERT INTO `article_category_ref` VALUES (29, 15);
INSERT INTO `article_category_ref` VALUES (29, 100000003);
INSERT INTO `article_category_ref` VALUES (28, 15);
INSERT INTO `article_category_ref` VALUES (28, 100000003);
INSERT INTO `article_category_ref` VALUES (NULL, 1);
INSERT INTO `article_category_ref` VALUES (NULL, 2);
INSERT INTO `article_category_ref` VALUES (NULL, 1);
INSERT INTO `article_category_ref` VALUES (NULL, 3);
INSERT INTO `article_category_ref` VALUES (NULL, 1);
INSERT INTO `article_category_ref` VALUES (NULL, 2);
INSERT INTO `article_category_ref` VALUES (43, 1);
INSERT INTO `article_category_ref` VALUES (43, 2);
INSERT INTO `article_category_ref` VALUES (44, 1);
INSERT INTO `article_category_ref` VALUES (44, 3);
INSERT INTO `article_category_ref` VALUES (45, 1);
INSERT INTO `article_category_ref` VALUES (45, 2);
INSERT INTO `article_category_ref` VALUES (46, 1);
INSERT INTO `article_category_ref` VALUES (46, 3);
INSERT INTO `article_category_ref` VALUES (47, 1);
INSERT INTO `article_category_ref` VALUES (47, 2);
INSERT INTO `article_category_ref` VALUES (48, 1);
INSERT INTO `article_category_ref` VALUES (48, 3);
INSERT INTO `article_category_ref` VALUES (40, 1);
INSERT INTO `article_category_ref` VALUES (40, 3);
INSERT INTO `article_category_ref` VALUES (49, 1);
INSERT INTO `article_category_ref` VALUES (49, 5);
INSERT INTO `article_category_ref` VALUES (50, 1);
INSERT INTO `article_category_ref` VALUES (50, 2);
INSERT INTO `article_category_ref` VALUES (51, 1);
INSERT INTO `article_category_ref` VALUES (51, 2);
INSERT INTO `article_category_ref` VALUES (53, 1);
INSERT INTO `article_category_ref` VALUES (53, 2);
INSERT INTO `article_category_ref` VALUES (54, 1);
INSERT INTO `article_category_ref` VALUES (54, 2);
INSERT INTO `article_category_ref` VALUES (55, 1);
INSERT INTO `article_category_ref` VALUES (55, 2);
INSERT INTO `article_category_ref` VALUES (56, 1);
INSERT INTO `article_category_ref` VALUES (56, 2);
INSERT INTO `article_category_ref` VALUES (57, 1);
INSERT INTO `article_category_ref` VALUES (57, 2);
INSERT INTO `article_category_ref` VALUES (58, 1);
INSERT INTO `article_category_ref` VALUES (58, 6);
INSERT INTO `article_category_ref` VALUES (59, 1);
INSERT INTO `article_category_ref` VALUES (59, 2);
INSERT INTO `article_category_ref` VALUES (60, 1);
INSERT INTO `article_category_ref` VALUES (60, 2);
INSERT INTO `article_category_ref` VALUES (62, 1);
INSERT INTO `article_category_ref` VALUES (62, 2);
INSERT INTO `article_category_ref` VALUES (63, 1);
INSERT INTO `article_category_ref` VALUES (63, 2);
INSERT INTO `article_category_ref` VALUES (64, 1);
INSERT INTO `article_category_ref` VALUES (64, 2);
INSERT INTO `article_category_ref` VALUES (65, 1);
INSERT INTO `article_category_ref` VALUES (65, 2);
INSERT INTO `article_category_ref` VALUES (66, 1);
INSERT INTO `article_category_ref` VALUES (66, 2);
INSERT INTO `article_category_ref` VALUES (67, 1);
INSERT INTO `article_category_ref` VALUES (67, 2);
INSERT INTO `article_category_ref` VALUES (68, 1);
INSERT INTO `article_category_ref` VALUES (68, 2);
INSERT INTO `article_category_ref` VALUES (69, 1);
INSERT INTO `article_category_ref` VALUES (69, 2);
INSERT INTO `article_category_ref` VALUES (70, 1);
INSERT INTO `article_category_ref` VALUES (70, 2);
INSERT INTO `article_category_ref` VALUES (71, 1);
INSERT INTO `article_category_ref` VALUES (71, 2);
INSERT INTO `article_category_ref` VALUES (72, 1);
INSERT INTO `article_category_ref` VALUES (72, 3);
INSERT INTO `article_category_ref` VALUES (74, 1);
INSERT INTO `article_category_ref` VALUES (74, 2);
INSERT INTO `article_category_ref` VALUES (75, 1);
INSERT INTO `article_category_ref` VALUES (75, 2);
INSERT INTO `article_category_ref` VALUES (76, 1);
INSERT INTO `article_category_ref` VALUES (76, 2);
INSERT INTO `article_category_ref` VALUES (77, 1);
INSERT INTO `article_category_ref` VALUES (77, 2);
INSERT INTO `article_category_ref` VALUES (79, 1);
INSERT INTO `article_category_ref` VALUES (79, 2);
INSERT INTO `article_category_ref` VALUES (80, 1);
INSERT INTO `article_category_ref` VALUES (80, 6);
INSERT INTO `article_category_ref` VALUES (52, 1);
INSERT INTO `article_category_ref` VALUES (52, 2);
INSERT INTO `article_category_ref` VALUES (81, 10);
INSERT INTO `article_category_ref` VALUES (81, 100000008);

-- ----------------------------
-- Table structure for article_tag_ref
-- ----------------------------
DROP TABLE IF EXISTS `article_tag_ref`;
CREATE TABLE `article_tag_ref`  (
  `article_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  PRIMARY KEY (`article_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_tag_ref
-- ----------------------------
INSERT INTO `article_tag_ref` VALUES (1, 1);
INSERT INTO `article_tag_ref` VALUES (1, 12);
INSERT INTO `article_tag_ref` VALUES (2, 12);
INSERT INTO `article_tag_ref` VALUES (3, 18);
INSERT INTO `article_tag_ref` VALUES (4, 6);
INSERT INTO `article_tag_ref` VALUES (5, 20);
INSERT INTO `article_tag_ref` VALUES (6, 21);
INSERT INTO `article_tag_ref` VALUES (7, 21);
INSERT INTO `article_tag_ref` VALUES (8, 13);
INSERT INTO `article_tag_ref` VALUES (9, 1);
INSERT INTO `article_tag_ref` VALUES (9, 19);
INSERT INTO `article_tag_ref` VALUES (10, 1);
INSERT INTO `article_tag_ref` VALUES (23, 2);
INSERT INTO `article_tag_ref` VALUES (23, 14);
INSERT INTO `article_tag_ref` VALUES (24, 15);
INSERT INTO `article_tag_ref` VALUES (24, 16);
INSERT INTO `article_tag_ref` VALUES (25, 3);
INSERT INTO `article_tag_ref` VALUES (25, 15);
INSERT INTO `article_tag_ref` VALUES (26, 31);
INSERT INTO `article_tag_ref` VALUES (26, 32);
INSERT INTO `article_tag_ref` VALUES (28, 26);
INSERT INTO `article_tag_ref` VALUES (28, 31);
INSERT INTO `article_tag_ref` VALUES (29, 28);
INSERT INTO `article_tag_ref` VALUES (30, 29);
INSERT INTO `article_tag_ref` VALUES (32, 33);
INSERT INTO `article_tag_ref` VALUES (33, 10);
INSERT INTO `article_tag_ref` VALUES (34, 2);
INSERT INTO `article_tag_ref` VALUES (34, 10);
INSERT INTO `article_tag_ref` VALUES (40, 1);
INSERT INTO `article_tag_ref` VALUES (40, 16);
INSERT INTO `article_tag_ref` VALUES (43, 1);
INSERT INTO `article_tag_ref` VALUES (44, 1);
INSERT INTO `article_tag_ref` VALUES (45, 1);
INSERT INTO `article_tag_ref` VALUES (46, 1);
INSERT INTO `article_tag_ref` VALUES (47, 1);
INSERT INTO `article_tag_ref` VALUES (48, 29);
INSERT INTO `article_tag_ref` VALUES (49, 1);
INSERT INTO `article_tag_ref` VALUES (49, 32);
INSERT INTO `article_tag_ref` VALUES (50, 1);
INSERT INTO `article_tag_ref` VALUES (50, 33);
INSERT INTO `article_tag_ref` VALUES (51, 1);
INSERT INTO `article_tag_ref` VALUES (52, 1);
INSERT INTO `article_tag_ref` VALUES (53, 1);
INSERT INTO `article_tag_ref` VALUES (54, 1);
INSERT INTO `article_tag_ref` VALUES (55, 1);
INSERT INTO `article_tag_ref` VALUES (56, 1);
INSERT INTO `article_tag_ref` VALUES (57, 1);
INSERT INTO `article_tag_ref` VALUES (58, 1);
INSERT INTO `article_tag_ref` VALUES (58, 33);
INSERT INTO `article_tag_ref` VALUES (59, 1);
INSERT INTO `article_tag_ref` VALUES (59, 33);
INSERT INTO `article_tag_ref` VALUES (60, 1);
INSERT INTO `article_tag_ref` VALUES (61, 1);
INSERT INTO `article_tag_ref` VALUES (61, 33);
INSERT INTO `article_tag_ref` VALUES (62, 1);
INSERT INTO `article_tag_ref` VALUES (63, 1);
INSERT INTO `article_tag_ref` VALUES (64, 1);
INSERT INTO `article_tag_ref` VALUES (64, 33);
INSERT INTO `article_tag_ref` VALUES (65, 1);
INSERT INTO `article_tag_ref` VALUES (66, 12);
INSERT INTO `article_tag_ref` VALUES (66, 33);
INSERT INTO `article_tag_ref` VALUES (67, 1);
INSERT INTO `article_tag_ref` VALUES (68, 1);
INSERT INTO `article_tag_ref` VALUES (68, 15);
INSERT INTO `article_tag_ref` VALUES (69, 1);
INSERT INTO `article_tag_ref` VALUES (70, 1);
INSERT INTO `article_tag_ref` VALUES (70, 15);
INSERT INTO `article_tag_ref` VALUES (71, 1);
INSERT INTO `article_tag_ref` VALUES (72, 1);
INSERT INTO `article_tag_ref` VALUES (72, 15);
INSERT INTO `article_tag_ref` VALUES (73, 29);
INSERT INTO `article_tag_ref` VALUES (74, 1);
INSERT INTO `article_tag_ref` VALUES (75, 1);
INSERT INTO `article_tag_ref` VALUES (75, 29);
INSERT INTO `article_tag_ref` VALUES (76, 1);
INSERT INTO `article_tag_ref` VALUES (76, 16);
INSERT INTO `article_tag_ref` VALUES (77, 3);
INSERT INTO `article_tag_ref` VALUES (77, 15);
INSERT INTO `article_tag_ref` VALUES (78, 1);
INSERT INTO `article_tag_ref` VALUES (79, 1);
INSERT INTO `article_tag_ref` VALUES (80, 1);
INSERT INTO `article_tag_ref` VALUES (81, 4);
INSERT INTO `article_tag_ref` VALUES (81, 39);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `category_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `category_pid` int(11) NULL DEFAULT NULL,
  `category_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `category_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `category_order` int(11) UNSIGNED NULL DEFAULT 1,
  `category_icon` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`category_id`) USING BTREE,
  UNIQUE INDEX `category_name`(`category_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100000009 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, 0, 'Java', 'Java语言', 0, 'fa fa-coffee');
INSERT INTO `category` VALUES (2, 1, 'Java基础', '', 1, '');
INSERT INTO `category` VALUES (3, 1, 'Core Java', '', 1, '');
INSERT INTO `category` VALUES (4, 1, '多线程并发编程', '', 1, '');
INSERT INTO `category` VALUES (5, 1, 'Sockets和IO', '', 1, '');
INSERT INTO `category` VALUES (6, 1, '设计模式和反射', '', 1, '');
INSERT INTO `category` VALUES (7, 1, 'JVM', '', 1, '');
INSERT INTO `category` VALUES (8, 1, 'JavaWeb', '', 1, '');
INSERT INTO `category` VALUES (9, 1, 'Java框架', '', 1, '');
INSERT INTO `category` VALUES (10, 0, 'C++', 'C++', 0, 'fa fa-cc');
INSERT INTO `category` VALUES (11, 0, '计算机科学', '', 1, 'fa fa-cubes');
INSERT INTO `category` VALUES (12, 0, '其他技术', '', 1, 'fa-snowflake-o fa');
INSERT INTO `category` VALUES (13, 11, '计算机网络', '', 1, '');
INSERT INTO `category` VALUES (14, 11, '数据结构和算法', '', 1, '');
INSERT INTO `category` VALUES (15, 11, '操作系统', '', 1, '');
INSERT INTO `category` VALUES (16, 12, '消息服务', '', 1, '');
INSERT INTO `category` VALUES (17, 12, '缓存服务', '', 1, '');
INSERT INTO `category` VALUES (18, 12, '数据库', '', 1, '');
INSERT INTO `category` VALUES (19, 12, '微服务', '', NULL, '');
INSERT INTO `category` VALUES (20, 12, '搜索引擎', '', NULL, '');
INSERT INTO `category` VALUES (21, 12, '权限框架', '', NULL, '');
INSERT INTO `category` VALUES (22, 12, '开发利器', '', NULL, '');
INSERT INTO `category` VALUES (1222, 100000000, 'Hello', '1111', 1, '11');
INSERT INTO `category` VALUES (100000008, 10, 'C++基础', 'C++基础', NULL, '');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `comment_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `comment_pid` int(11) UNSIGNED NULL DEFAULT 0,
  `comment_pname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `comment_article_id` int(11) UNSIGNED NULL DEFAULT NULL,
  `comment_author_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `comment_author_email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `comment_author_url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `comment_author_avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `comment_content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `comment_agent` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `comment_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `comment_create_time` datetime(0) NULL DEFAULT NULL,
  `comment_role` int(1) NULL DEFAULT NULL,
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 0, '', 81, '二五仔', 'admin@163.com', 'http://www.betterts.cn', 'http://cn.gravatar.com/avatar/bb83926df8ed00de9d10a2ad79dbf7f2?s=128&d=identicon&r=PG', '评论测试', NULL, '1.1.1.1', '2020-04-02 11:33:21', 0);

-- ----------------------------
-- Table structure for link
-- ----------------------------
DROP TABLE IF EXISTS `link`;
CREATE TABLE `link`  (
  `link_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `link_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `link_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `link_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `link_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `link_owner_nickname` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `link_owner_contact` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `link_update_time` datetime(0) NULL DEFAULT NULL,
  `link_create_time` datetime(0) NULL DEFAULT NULL,
  `link_order` int(2) UNSIGNED NULL DEFAULT 1,
  `link_status` int(1) UNSIGNED NULL DEFAULT 1,
  PRIMARY KEY (`link_id`) USING BTREE,
  UNIQUE INDEX `link_name`(`link_name`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of link
-- ----------------------------
INSERT INTO `link` VALUES (11, 'https://www.nowcoder.com/', '牛客网', NULL, '', NULL, '', '2020-04-01 00:14:48', '2020-04-01 00:14:48', 2, 1);
INSERT INTO `link` VALUES (10, 'http:///www.betterts.cn', 'BetterTs博客', NULL, '鸢飞戾天者，望峰息心；经纶世务者，窥欲忘反', NULL, 'QQ: 489635779', '2020-04-01 01:30:10', '2020-03-31 21:35:31', 1, 1);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menu_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menu_level` int(11) NULL DEFAULT NULL,
  `menu_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menu_order` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`menu_id`) USING BTREE,
  UNIQUE INDEX `menu_name`(`menu_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '留言板', '/message', 2, 'fa fa-comment', 1);
INSERT INTO `menu` VALUES (2, 'LeetCode', 'https://leetcode.com/problemset/all', 2, 'ssss', 3);
INSERT INTO `menu` VALUES (3, '关于本站', '/aboutSite', 1, 'fa fa-info', 1);
INSERT INTO `menu` VALUES (4, '文章归档', '/articleFile', 1, 'fa-list-alt fa', 2);
INSERT INTO `menu` VALUES (5, '申请友链', '/applyLink', 1, 'fa fa-link', 3);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT,
  `notice_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `notice_content` varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `notice_create_time` datetime(0) NULL DEFAULT NULL,
  `notice_update_time` datetime(0) NULL DEFAULT NULL,
  `notice_status` int(1) UNSIGNED NULL DEFAULT 1,
  `notice_order` int(2) NULL DEFAULT NULL,
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (5, '博客建站通知', '<p>本博客系统基于 SSM框架和JSP实现，Dao层主要使用的是基于注解的开发方法，且后台集成了Markdown插件，支持常见的markdown语法和本地上传插入图片。</p><p>设计思路主要参考了GitHub上优秀的<a target=\"_self\" href=\"https://github.com/saysky/ForestBlog\">开源项目</a>。</p><p><br></p><p>博客地址：<a target=\"_self\" href=\"http://www.betterts.cn\">http://www.betterts.cn</a></p><p><br></p><p>Github地址：<a href=\"https://github.com/betterts\">https://github.com/TangShaoquan</a></p>', '2020-04-01 01:22:51', '2020-04-01 09:19:46', 1, 1);

-- ----------------------------
-- Table structure for options
-- ----------------------------
DROP TABLE IF EXISTS `options`;
CREATE TABLE `options`  (
  `option_id` int(11) NOT NULL AUTO_INCREMENT,
  `option_site_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `option_site_descrption` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `option_meta_descrption` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `option_meta_keyword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `option_aboutsite_avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `option_aboutsite_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `option_aboutsite_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `option_aboutsite_wechat` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `option_aboutsite_qq` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `option_aboutsite_github` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `option_aboutsite_weibo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `option_tongji` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `option_status` int(1) NULL DEFAULT 1,
  PRIMARY KEY (`option_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of options
-- ----------------------------
INSERT INTO `options` VALUES (1, '望峰息心', '志存高远，求知向上', '望峰博客,程序人生新起点', 'BetterTs', 'http://www.betterts.cn/upload/2020/4/adminlogo.jpg', '博客初心', '程序人生，永不止步', 'http://www.betterts.cn/upload/2020/4/weixin.jpg', '489635779', 'BetterTs', '', NULL, 1);

-- ----------------------------
-- Table structure for page
-- ----------------------------
DROP TABLE IF EXISTS `page`;
CREATE TABLE `page`  (
  `page_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `page_key` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `page_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `page_content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `page_create_time` datetime(0) NULL DEFAULT NULL,
  `page_update_time` datetime(0) NULL DEFAULT NULL,
  `page_view_count` int(10) UNSIGNED NULL DEFAULT 0,
  `page_comment_count` int(5) UNSIGNED NULL DEFAULT 0,
  `page_status` int(1) UNSIGNED NULL DEFAULT 1,
  PRIMARY KEY (`page_id`) USING BTREE,
  UNIQUE INDEX `page_key`(`page_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of page
-- ----------------------------
INSERT INTO `page` VALUES (1, 'map', '站点地图', 'null', NULL, NULL, 0, 0, 2);
INSERT INTO `page` VALUES (2, 'articleFile', '文章归档', 'null', NULL, NULL, 0, 0, 2);
INSERT INTO `page` VALUES (3, 'message', '留言板', 'null', NULL, NULL, 0, 0, 2);
INSERT INTO `page` VALUES (4, 'applyLink', '申请友链', 'null', NULL, NULL, 0, 0, 2);
INSERT INTO `page` VALUES (6, 'aboutSite', '关于本站', '<p><b>待更新……</b></p>', '2020-04-02 07:23:37', '2020-04-02 07:24:58', NULL, NULL, 1);

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `tag_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tag_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`tag_id`) USING BTREE,
  UNIQUE INDEX `tag_name`(`tag_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES (1, 'Java', 'Java');
INSERT INTO `tag` VALUES (2, '算法', '');
INSERT INTO `tag` VALUES (3, '数据结构', '');
INSERT INTO `tag` VALUES (4, 'C', '');
INSERT INTO `tag` VALUES (5, '操作系统', '');
INSERT INTO `tag` VALUES (6, '计算机网络', '');
INSERT INTO `tag` VALUES (8, '面试题', '');
INSERT INTO `tag` VALUES (9, '数据库', '');
INSERT INTO `tag` VALUES (10, 'MySQL', '');
INSERT INTO `tag` VALUES (11, 'Spring', '');
INSERT INTO `tag` VALUES (12, 'SpringMVC', '');
INSERT INTO `tag` VALUES (13, 'MyBatis', '');
INSERT INTO `tag` VALUES (14, 'JVM', '');
INSERT INTO `tag` VALUES (15, '设计模式', '');
INSERT INTO `tag` VALUES (16, '网络编程', '');
INSERT INTO `tag` VALUES (17, 'IO', '');
INSERT INTO `tag` VALUES (18, 'JSP', '');
INSERT INTO `tag` VALUES (19, 'Servlet', '');
INSERT INTO `tag` VALUES (20, 'JavaWeb', '');
INSERT INTO `tag` VALUES (21, 'Hibernate', '');
INSERT INTO `tag` VALUES (22, 'SQL', '');
INSERT INTO `tag` VALUES (24, 'Redis', 'redis');
INSERT INTO `tag` VALUES (25, 'SPA', '');
INSERT INTO `tag` VALUES (26, 'SpringCloud', '');
INSERT INTO `tag` VALUES (27, 'Dubbo', '');
INSERT INTO `tag` VALUES (28, 'EDAS', '');
INSERT INTO `tag` VALUES (29, 'MongoDB', '');
INSERT INTO `tag` VALUES (31, 'SpringBoot', '');
INSERT INTO `tag` VALUES (32, 'ElasticSearch', '');
INSERT INTO `tag` VALUES (33, 'Docker', '');
INSERT INTO `tag` VALUES (34, 'Jenkins', '');
INSERT INTO `tag` VALUES (35, 'Vue', '');
INSERT INTO `tag` VALUES (36, 'IDEA', '');
INSERT INTO `tag` VALUES (37, 'Shiro', '');
INSERT INTO `tag` VALUES (38, 'RocketMQ', '');
INSERT INTO `tag` VALUES (39, 'C++', 'C++');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `user_pass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `user_nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `user_email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `user_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `user_avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_last_login_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_register_time` datetime(0) NULL DEFAULT NULL,
  `user_last_login_time` datetime(0) NULL DEFAULT NULL,
  `user_status` int(1) UNSIGNED NULL DEFAULT 1,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_name`(`user_name`) USING BTREE,
  UNIQUE INDEX `user_email`(`user_email`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'Admin', 'admin', 'Betterts', 'admin@163.com', 'https://betterts', 'http://www.betterts.cn/upload/2020/4/adminlogo.jpg', '1.1.1.1', '2020-3-27 21:56:33', '2020-04-02 16:10:38', 1);

SET FOREIGN_KEY_CHECKS = 1;
