# -*- coding: utf-8 -*-
import random
from faker import Faker
from datetime import datetime, timezone, timedelta
fake = Faker()

def create_dummy_data(farm_num):
    sql_queries = []
    
    for num in range(1, farm_num + 1):
        # 몇개의 청약을 넣을 것인가?
        # 진행예정, 진행중 : 0~1, 진행예정 + 진행중 <= 1, 종료 1~3
        subs = {}
        # 0~1 사이 랜덤
        subs['proceed'] = random.randint(0,1)
        subs['progress'] = random.randint(0,1)
        subs['end'] = random.randint(1,3)
        
        # 진행예정과 진행중 모두 1이면
        if subs['proceed'] and subs['progress']:
            # 0으로 만들 것 랜덤 선택
            drop_choice = random.choice(['proceed', 'progress'])
            # 0으로 만듦
            subs[drop_choice] = 0
        
        # 농장 코드
        farm_code = num
        
        # 청약 만들기
        # 고정 값
        # 조각 가격 = 10000원 고정 
        confirm_price = 10000
        # 인당 구매 개수 제한 = 50개 고정
        limit_num = 50
        # 농장주에게 나눠줄 수익 비율 = 10% 고정(임시)
        partner_farm_rate = 10
        # 총 발행 토큰 수 = 농장 규모 m^2 만큼
        total_token_count = farm_scales[f'{num}']
        # 청약의 총 수익률 기본값
        total_proceed = 0

        # 진행 예정 청약
        for i in range(subs['proceed']):
            # 시작 시간 = 1달 후 ~ 1년 후 사이
            started_time = fake.date_time_between(start_date='+1m', end_date='+1y').replace(hour=9, minute=0, second=0).strftime('%Y-%m-%d %H:%M:%S')
            # 종료 시간 = 시작 시간 + 2주 뒤
            ended_time = (datetime.strptime(started_time, '%Y-%m-%d %H:%M:%S') + timedelta(weeks=2)).replace(hour=8, minute=59, second=59).strftime('%Y-%m-%d %H:%M:%S')
            # 수익률은 0~30.00% 소수점 두자리까지
            return_rate = round(random.uniform(0, 30), 2)
            
            # sql문 생성
            sql = f"INSERT INTO subscription_tb (farm_code, confirm_price, started_time, ended_time, limit_num, return_rate, total_token_count, partner_farm_rate, total_proceed) VALUES ('{farm_code}', '{confirm_price}', '{started_time}', '{ended_time}', '{limit_num}', '{return_rate}', '{total_token_count}', '{partner_farm_rate}', {total_proceed});"

            sql_queries.append(sql)


        # 진행중 청약
        for i in range(subs['progress']):
            # 시작 시간 = 1주 전 ~ 현재 사이
            started_time = fake.date_time_between(start_date='-1w', end_date='now').replace(hour=9, minute=0, second=0).strftime('%Y-%m-%d %H:%M:%S')
            # 종료 시간 = 시작 시간 + 2주 뒤
            ended_time = (datetime.strptime(started_time, '%Y-%m-%d %H:%M:%S') + timedelta(weeks=2)).replace(hour=8, minute=59, second=59).strftime('%Y-%m-%d %H:%M:%S')
            # 수익률은 0~30.00% 소수점 두자리까지
            return_rate = round(random.uniform(0, 30), 2)

            # sql문 생성
            sql = f"INSERT INTO subscription_tb (farm_code, confirm_price, started_time, ended_time, limit_num, return_rate, total_token_count, partner_farm_rate, total_proceed) VALUES ('{farm_code}', '{confirm_price}', '{started_time}', '{ended_time}', '{limit_num}', '{return_rate}', '{total_token_count}', '{partner_farm_rate}', {total_proceed});"

            sql_queries.append(sql)


        # 진행종료 청약
        for i in range(subs['end']):
            # 시작 시간 = 2년 전 ~ 2주 전
            started_time = fake.date_time_between(start_date='-2y', end_date='-2w').replace(hour=9, minute=0, second=0).strftime('%Y-%m-%d %H:%M:%S')
            # 종료 시간 = 시작 시간 + 2주 뒤
            ended_time = (datetime.strptime(started_time, '%Y-%m-%d %H:%M:%S') + timedelta(weeks=2)).replace(hour=8, minute=59, second=59).strftime('%Y-%m-%d %H:%M:%S')
            # 수익률은 0~30.00% 소수점 두자리까지
            return_rate = round(random.uniform(0, 30), 2)
            # 청약의 총 수익률 종료된 것은 값이 존재
            total_proceed = random.randint(4, 21)

            # sql문 생성
            sql = f"INSERT INTO subscription_tb (farm_code, confirm_price, started_time, ended_time, limit_num, return_rate, total_token_count, partner_farm_rate, total_proceed) VALUES ('{farm_code}', '{confirm_price}', '{started_time}', '{ended_time}', '{limit_num}', '{return_rate}', '{total_token_count}', '{partner_farm_rate}', {total_proceed});"

            sql_queries.append(sql)
        
        
    return sql_queries

# 내 DB에 저장된 farm_scale 칼럼값
farm_scale_ls = [
    860, 1976, 5206, 6901, 6091, 7682, 6424, 9707, 8826, 4317,
    995, 6519, 8429, 2536, 9642, 4759, 9078, 8872, 8160, 1447,
    1992, 2421, 9148, 7479, 3706, 3407, 3560, 1580, 1474, 1860,
    2612, 9434, 6087, 9608, 9915, 4773, 685, 1248, 3907, 9126,
    5710, 1009, 4995, 2733, 3934, 2039, 5874, 6004, 1086, 7726,
    758, 1464, 4684, 6597, 523, 5994, 7107, 2444, 4778, 8120,
    5438, 8218, 9994, 8071, 6294, 5069, 7763, 2457, 5945, 1595,
    3160, 4809, 7426, 3520, 624, 6814, 3226, 5363, 965, 9351,
    3620, 4185, 5484, 708, 2737, 6894, 8806, 4087, 8931, 6363,
    1829, 3743, 8175, 9823, 3154, 2468, 9531, 7770, 4353, 1681,
    2428, 5536, 3226, 6055, 5786, 5694, 5704, 5969, 6702, 5002,
    5379, 4981, 3888, 4655, 4742, 9249, 3424, 3939, 9601, 3127,
    2291, 3773, 892, 1150, 7749, 5756, 3351, 898, 3310, 9719,
    7890, 6941, 1046, 2174, 9512, 5922, 9247, 8432, 1639, 5652,
    3977, 2766, 3638, 2129, 5007, 7409, 7909, 1078, 6114, 2177,
    7663, 9935, 9910, 3342, 576, 5944, 9429, 3649, 4764, 7734,
    4470, 3201
]

# SQL 쿼리 생성
farm_num = int(input("농장 개수를 입력하세요: "))
# farm_code : farm_scale 형태로 딕셔너리로 만듦
farm_scales = {f'{i}':farm_scale_ls[i-1] for i in range(1, farm_num + 1)}
queries = (create_dummy_data(farm_num))

with open('subs_dummy_data_queries.txt', 'w', encoding='utf-8') as f:
    for query in queries:
        f.write(query + '\n')
print('생성 완료')
