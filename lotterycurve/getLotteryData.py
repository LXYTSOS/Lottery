__author__ = 'sl169'
#coding=utf-8

import mysql.connector
from matplotlib.pyplot import plot
from matplotlib.pyplot import show
import predicte as prdct

#连接数据库
conn = mysql.connector.connect(user='root', password='root', database='lottery_forecast', use_unicode=True)
cursor = conn.cursor()

#查询出双色球历史数据
# cursor.execute('select * from tb_lottery order by phase desc limit 10')
cursor.execute('select * from tb_lottery')
values = cursor.fetchall()

#将双色球历史数据存入列表中
phase = []
red1 = []
red2 = []
red3 = []
red4 = []
red5 = []
red6 = []
blue = []
luckyblue1 = []

for i in values:
    #print i[2:9]
    phase.append(i[1])
    red1.append(i[2])
    red2.append(i[3])
    red3.append(i[4])
    red4.append(i[5])
    red5.append(i[6])
    red6.append(i[7])
    blue.append(i[8])
    # luckyblue1.append(i[9])
    # print '%2d %2d %2d %2d %2d %2d %2d' % i[2:9]

# luckyblue2 = []
# for i in range(len(luckyblue1)):
#     if luckyblue1[i]!=0:
#         luckyblue2.append(luckyblue1[i])

#查询误差数据
cursor.execute('select * from tb_error')
errors = cursor.fetchall()

red1Error = 0
red2Error = 0
red3Error = 0
red4Error = 0
red5Error = 0
red6Error = 0
blueError = 0

#将每个球误差累加
for i in errors:
    # print i[2:9]
    red1Error = red1Error + i[2]
    red2Error = red2Error + i[3]
    red3Error = red3Error + i[4]
    red4Error = red4Error + i[5]
    red5Error = red5Error + i[6]
    red6Error = red6Error + i[7]
    blueError = blueError + i[8]

cursor.execute('select * from tb_error order by phase desc limit 1')
lastErrors = cursor.fetchall()
lastRed1 = 0
lastRed2 = 0
lastRed3 = 0
lastRed4 = 0
lastRed5 = 0
lastRed6 = 0
lastBlue = 0
for i in lastErrors:
    lastRed1 = i[2]
    lastRed2 = i[3]
    lastRed3 = i[4]
    lastRed4 = i[5]
    lastRed5 = i[6]
    lastRed6 = i[7]
    lastBlue = i[8]

#调用预测算法，然后加上对应球的误差平均值
# pred1=prdct.prediction(red1) + red1Error/len(errors)
# pred2=prdct.prediction(red2) + red2Error/len(errors)
# pred3=prdct.prediction(red3) + red3Error/len(errors)
# pred4=prdct.prediction(red4) + red4Error/len(errors)
# pred5=prdct.prediction(red5) + red5Error/len(errors)
# pred6=prdct.prediction(red6) + red6Error/len(errors)
# pblue=prdct.prediction(blue) + blueError/len(errors)

# pred1=prdct.prediction(red1) + red1Error/len(errors) + lastRed1
# pred2=prdct.prediction(red2) + red2Error/len(errors) + lastRed2
# pred3=prdct.prediction(red3) + red3Error/len(errors) + lastRed3
# pred4=prdct.prediction(red4) + red4Error/len(errors) + lastRed4
# pred5=prdct.prediction(red5) + red5Error/len(errors) + lastRed5
# pred6=prdct.prediction(red6) + red6Error/len(errors) + lastRed6
# pblue=prdct.prediction(blue) + blueError/len(errors) + lastBlue
#
pred1=prdct.prediction(red1) + lastRed1/2
pred2=prdct.prediction(red2) + lastRed2/2
pred3=prdct.prediction(red3) + lastRed3/2
pred4=prdct.prediction(red4) + lastRed4/2
pred5=prdct.prediction(red5) + lastRed5/2
pred6=prdct.prediction(red6) + lastRed6/2
pblue=prdct.prediction(blue) + lastBlue/2
pphase = int(phase[len(phase)-1]) + 1

print pphase,pred1,pred2,pred3,pred4,pred5,pred6,pblue

print prdct.prediction(red1),prdct.prediction(red2),prdct.prediction(red3),\
    prdct.prediction(red4),prdct.prediction(red5),prdct.prediction(red6),\
    prdct.prediction(blue)
# print red1Error/len(errors),red2Error/len(errors),red3Error/len(errors),\
#     red4Error/len(errors),red5Error/len(errors),red6Error/len(errors),\
#     blueError/len(errors)
print lastRed1,lastRed2,lastRed3,lastRed4,lastRed5,lastRed6,lastBlue
#将预测数据存入预测表
sql="insert into tb_forecast(phase,red_1,red_2,red_3,red_4,red_5,red_6,blue,lucky_blue) \
values(%s,%f,%f,%f,%f,%f,%f,%f,%f)"%(pphase,pred1,pred2,pred3,pred4,pred5,pred6,pblue,0)
cursor.execute(sql)
cursor.rowcount
conn.commit()
cursor.close()
print "prediction inserted successfully"

#将预测的数据和历史数据一起以图的形式显示出来
red1.append(pred1)
red2.append(pred2)
red3.append(pred3)
red4.append(pred4)
red5.append(pred5)
red6.append(pred6)
blue.append(pblue)

plot(red1,lw=1.0)
plot(red2,lw=1.0)
plot(red3,lw=1.0)
plot(red4,lw=1.0)
plot(red5,lw=1.0)
plot(red6,lw=1.0)
plot(blue,lw=2.0)
# plot(luckyblue2,lw=1.0)
show()