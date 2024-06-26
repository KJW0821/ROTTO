def create_dummy_data(n):
    sql_queries = []
    for num in range(1, n+1):
        farm_code = subscription_tb_farm_code_ls[num-1]
        subscription_code = num
        farm_scale = farm_scale_ls[farm_code-1]
        for key, value in expenditure_content_dict.items():
            expenditure_content = key
            expenses = value * farm_scale
            sql = f"INSERT INTO expense_detail_tb (subscription_code, expenditure_content, expenses) VALUES ('{subscription_code}', '{expenditure_content}', '{expenses}');"
            sql_queries.append(sql)

    return sql_queries


# 내 DB에 저장된 farm_scale 칼럼값(162개)
farm_scale_ls = [
    14400, 11700, 12600, 8800, 8200, 14200, 10100, 14500, 10500, 4800, 
    12200, 9200, 12100, 11900, 7200, 6200, 9800, 6100, 14900, 12700, 
    8200, 11500, 4200, 9000, 6700, 13900, 6300, 8100, 10100, 14700, 
    14800, 11500, 7800, 12100, 13800, 11500, 11800, 14200, 13100, 13800, 
    8500, 6600, 6000, 11200, 11400, 13600, 7900, 4500, 7200, 9500, 
    9100, 7700, 10900, 8500, 10800, 12300, 15000, 9300, 8400, 14100, 
    14200, 7400, 6900, 5100, 13600, 8800, 8500, 5000, 5700, 12800, 
    10500, 10900, 13700, 4500, 5300, 7000, 8300, 6400, 9400, 13300, 
    11100, 6800, 5900, 14400, 4700, 14100, 4400, 7900, 11100, 14800, 
    4000, 5200, 14700, 14500, 4900, 5300, 11600, 6800, 10000, 12800, 
    9600, 7300, 5000, 11900, 8600, 14300, 11100, 8100, 9200, 12400, 
    12500, 6400, 13300, 5300, 13900, 4200, 6600, 10500, 11300, 5100, 
    10200, 4200, 11000, 6000, 14500, 14700, 6400, 13900, 13900, 8200, 
    13300, 7700, 5500, 14600, 14500, 13000, 7400, 14200, 9600, 8700, 
    13400, 4200, 13700, 8400, 13400, 5100, 7000, 13500, 9700, 12200, 
    8400, 9800, 9200, 5900, 8600, 13800, 13500, 12300, 10100, 11900, 
    7300, 5000
]

subscription_tb_farm_code_ls = [
    1, 1, 2, 2, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 7, 7, 7, 8, 8, 8, 8, 9, 9, 
    10, 10, 11, 11, 11, 12, 12, 13, 13, 14, 14, 14, 15, 15, 15, 15, 16, 16, 16, 
    16, 17, 17, 18, 18, 18, 18, 19, 19, 19, 19, 20, 20, 21, 21, 22, 22, 22, 22, 
    23, 23, 23, 24, 24, 24, 25, 25, 25, 26, 26, 27, 27, 27, 28, 28, 28, 28, 29, 
    30, 30, 31, 31, 32, 32, 32, 32, 33, 33, 34, 34, 35, 35, 35, 35, 36, 36, 37, 
    37, 37, 38, 38, 39, 39, 40, 40, 40, 41, 41, 42, 42, 42, 42, 43, 43, 44, 44, 
    44, 44, 45, 45, 45, 45, 46, 46, 47, 47, 47, 48, 48, 48, 48, 49, 49, 50, 51, 
    52, 52, 52, 53, 53, 54, 54, 55, 55, 56, 56, 57, 57, 57, 57, 58, 58, 59, 59, 
    60, 60, 61, 61, 62, 62, 63, 64, 64, 65, 65, 65, 66, 66, 66, 67, 67, 67, 68, 
    68, 69, 69, 69, 69, 70, 71, 71, 72, 72, 73, 73, 73, 73, 74, 74, 74, 75, 75, 
    75, 75, 76, 76, 76, 76, 77, 77, 77, 78, 78, 78, 78, 79, 79, 80, 80, 81, 81, 
    81, 82, 82, 83, 83, 83, 83, 84, 84, 84, 84, 85, 85, 86, 86, 86, 86, 87, 87, 
    87, 88, 88, 89, 89, 89, 89, 90, 90, 91, 91, 91, 92, 93, 93, 93, 93, 94, 94, 
    94, 95, 95, 96, 96, 96, 96, 97, 97, 98, 98, 99, 99, 99, 99, 100, 100, 101, 
    101, 101, 102, 102, 102, 102, 103, 103, 103, 103, 104, 104, 105, 105, 106, 
    106, 106, 107, 107, 108, 108, 108, 109, 109, 109, 110, 110, 110, 111, 111, 
    111, 112, 112, 112, 113, 113, 113, 114, 114, 114, 114, 115, 115, 115, 115, 
    116, 116, 116, 116, 117, 117, 117, 118, 118, 118, 119, 120, 120, 120, 121, 
    121, 121, 122, 122, 123, 123, 123, 123, 124, 124, 125, 125, 126, 126, 126, 
    127, 127, 127, 128, 129, 129, 129, 130, 130, 130, 130, 131, 131, 131, 132, 
    132, 132, 133, 133, 134, 135, 135, 136, 136, 137, 137, 137, 137, 138, 138, 
    138, 138, 139, 139, 139, 139, 140, 140, 140, 141, 141, 141, 141, 142, 142, 
    142, 142, 143, 143, 143, 144, 144, 144, 145, 145, 146, 146, 146, 147, 147, 
    147, 148, 148, 148, 148, 149, 149, 149, 149, 150, 150, 151, 151, 151, 151, 
    152, 152, 152, 153, 153, 153, 154, 154, 154, 155, 155, 155, 156, 156, 156, 
    157, 157, 157, 158, 158, 158, 159, 159, 160, 160, 160, 161, 161, 161, 162, 162
]

expenditure_content_dict = {'수확비용': 1000, '열매선별비용': 150, '기타운영비': 400}

n = int(input("청약 개수를 입력하세요: "))
queries = (create_dummy_data(n))
print(queries)
with open('expense_detail_dummy_data_queries.txt', 'w', encoding='utf-8') as f:
    for query in queries:
        f.write(query + '\n')
print('생성 완료')
