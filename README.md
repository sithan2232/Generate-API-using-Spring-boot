# Practice-Spring-Boot(PART 1)

Remind myself

โปรเเกรมดึงข้อมูลจาก database เเละส่งต่อโดยใช้ API โดยการสร้าง API ให้ environment อื่นเรียก 

Dependency
(Web,
Sql,
Dev,
JPA)

ตัวอย่าง API สําหรับ log-in

![](https://github.com/sithan2232/Practice-Spring-Boot/blob/master/images/login/log-in.png)

API นี้จะทําการเช็ค ความถูต้องของ username เเละ password กับ database
โดยใช้ BCrypt encoder ในการรักษาความปลอดภัย(ในโปรเจคนี้ยก bcrypt เเบบง่ายมาใช้)

เมื่อ login สําเร็จตัวโปรเกรมจะทําการ Update status ของผู้ใช้เป็น active

![](https://github.com/sithan2232/Practice-Spring-Boot/blob/master/images/login/log-inResult.png)


ตัวอย่าง API สําหรับดึงข้อมูล Post (topic,description,color) , คนเขียนโพส เเละ ข้อมูลขอคนที่มากดไลค์

![](https://github.com/sithan2232/Practice-Spring-Boot/blob/master/images/getpost/Screen%20Shot%202563-09-20%20at%2019.15.29.png)

โดยข้อมูลที่ response กลับมาจาก API นี้มันคือการรวม(join) ข้อมูลของ 3 ตารางเเละส่งออกมาเป็นข้อมูล JSON ก้อนเดียว

Post table

![](https://github.com/sithan2232/Practice-Spring-Boot/blob/master/images/getpost/Screen%20Shot%202563-09-20%20at%2019.15.58.png)

Like table

![](https://github.com/sithan2232/Practice-Spring-Boot/blob/master/images/getpost/Screen%20Shot%202563-09-20%20at%2019.16.08.png)

User table

![](https://github.com/sithan2232/Practice-Spring-Boot/blob/master/images/getpost/Screen%20Shot%202563-09-20%20at%2019.16.17.png)
