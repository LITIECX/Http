makefile
===

1.makefile基础学习
---

### 1.基本规则原理

预处理 ：选择对应的C代码  -E

编译：c代码转化为汇编代码  -S

汇编：汇编代码转换成二进制 -C

链接：可执行文件按照关系链接在一起 -o



target...   :   prerequisites ...

​			command

```makefile
all:cli
cli:client.o net.o cJSON.o user.o file.o
	gcc -o cli client.o net.o cJSON.o user.o file.o -lm
client.o:client.c
	gcc -c client.c
net.o:net.c
	gcc -c net.c
cJSON.o: cJSON.c
	gcc -c cJSON.c
user.o:user.c
	gcc -c user.c
file.o:file.c
	gcc -c file.c
clean:
	rm -rf *.o ser
```



clean 不是一个文件，只不过是一个动作名字。要想触发执行，必须执行如下命令 make clean。

### 2.makefile中使用变量

在上面文件中，[.o]文件被重复使用两次。

```makefile
#first_make
obj= client.o net.o cJSON.o user.o file.o
all:cli
cli:$(obj)
	gcc -o cli $(obj) -lm
client.o:client.c
	gcc -c client.c
net.o:net.c
	gcc -c net.c
cJSON.o: cJSON.c
	gcc -c cJSON.c
user.o:user.c
	gcc -c user.c
file.o:file.c
	gcc -c file.c
clean:
	rm -rf *.o ser
```

### 3.makefile的类型推导

根据自动类型推导，可以进一步简化makefile。

```makefile
obj= client.o net.o cJSON.o user.o file.o
all:cli
cli:$(obj)
	gcc -o cli $(obj) -lm
client.o:client.c
net.o:net.c	
cJSON.o: cJSON.c	
user.o:user.c	
file.o:file.c
.PHONY:clean
clean:
	rm -rf *.o ser
```

### 4.嵌套执行make

​	在一些大工程中，我们会把不同的模块或是不同功能的源文件放在不同的目录中，我们可以在每一个目录中都书写一个该目录的makefile,这有助于让makefile变得更加简洁。

```makefile
subsystem:
	cd subdir && $(MAKE)#定义$(MAKE)宏变量的意思，make的执行需要一些参数。
#其等价与
subsystem:
	$(MAKE) -C subdir
```

在嵌套执行中比较有用的参数，“-w”或“--print-directory”会在make的过程中输出一些信息，让你看到目前的工作目录。

```makefile
make -w
```

make: Entering directory '/mnt/baidupan/src/client'

make: Leaving directory '/mnt/baidupan/src/client'

当使用“-C”参数指定make下层makefile时，“-w”会被自动打开。

2.makefile核心
---

### 2.1makefile常见的预定义变量

| 命名格式 |               含义                |
| :------: | :-------------------------------: |
|    AR    | 库文件维护程序的名称，默认值为ar  |
|    AS    |    汇编程序的名称，默认值为as     |
|    CC    |     c编译器的名称，默认值为cc     |
|   CPP    | c预编译器的名称，默认值为$(CC)-E  |
|   CXX    |   C++编译器的名称，默认值为g++    |
|    FC    | FORTRAN编译器的名称，默认值为f77  |
|    RM    | 文件删除程序的名称，默认值为rm –f |
| ARFLAGS  |  库文件维护程序的选项，无默认值   |
| ASFLAGS  |     汇编程序的选项，无默认值      |
|  CFLAGS  |      C编译器的选项，无默认值      |
| CPPFLAGS |      C预编译的选项，无默认值      |
| CXXFLAGS |     C++编译器的选项，无默认值     |
|  FFLAGS  |   FORTRAN编译器的选项，无默认值   |

```makefile
#$^  依赖不重复
#$@  目标
#@ 不显示命令执行
# -失败不停止

all:cli
cli:client.o net.o cJSON.o user.o file.o
	gcc $^ -o $@  -lm
client.o:client.c
net.o:net.c	
cJSON.o: cJSON.c	
user.o:user.c	
file.o:file.c
.PHONY:clean
clean:
	rm -rf *.o ser

```

