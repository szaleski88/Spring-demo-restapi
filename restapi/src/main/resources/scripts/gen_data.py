from datetime import datetime, timedelta


with open('data.sql', "w") as f:
    for i in range(1, 101):
        line = f"insert into post(id, title, content, created) values ({i}, 'Test post {i}',  'Content {i}',  '" + str(datetime.now() - timedelta(days=100 - i)) + "');\n"
        f.write(line)
        print(line)

    for i in range(1, 101):
        postId = int(1 + i / 10)

        line = f"insert into comment(id, post_id, content, created) values ({i}, '{postId}',  'Content {i}',  '" + str(datetime.now() - timedelta(days=100 - i)) + "');\n"
        print(line)
        f.write(line)


