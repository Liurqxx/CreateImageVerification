from PIL import Image, ImageDraw, ImageFont, ImageFilter
import random
from io import BytesIO


# 随机字母:
def rnd_char():
    # 随机ASCII表
    # 65-90   97-122
    result = [x for x in range(65, 122) if x <= 90 or x >= 97]
    res = random.randint(0, len(result))
    return chr(result[res])


# 随机颜色:
def rnd_color():
    return random.randint(0, 245), random.randint(0, 245), random.randint(0, 245)


# 生成验证码和图片
def generate_code(file_name='code'):
    # 240 x 60:
    width = 60 * 4
    height = 60
    image = Image.new('RGB', (width, height), (255, 255, 255))
    # 创建Font对象
    font = ImageFont.truetype('arial.ttf', 36)
    # 创建Draw对象
    draw = ImageDraw.Draw(image)
    # 随机生成两条直线（一条贯穿上半部，一条贯穿下半部）
    draw.line((0, 0 + random.randint(0, height // 2),
               width, 0 + random.randint(0, height // 2)),
              fill=rnd_color())
    draw.line((0, height - random.randint(0, height // 2),
               width, height - random.randint(0, height // 2)),
              fill=rnd_color())
    # 输出文字
    code_str = ''
    for t in range(4):
        tmp = rnd_char()
        draw.text((60 * t + 10, 10), tmp, font=font, fill=rnd_color())
        code_str += tmp
    # 模糊处理
    # image = image.filter(ImageFilter.BLUR)
    image = image.filter(ImageFilter.EDGE_ENHANCE)
    out = BytesIO()
    image.save(out, format='png')
    # image.save(file_name + '.png', 'png')
    # 返回验证码内容,文件名
    return code_str, file_name, out.getvalue()


if __name__ == '__main__':
    print(generate_code('code'))
