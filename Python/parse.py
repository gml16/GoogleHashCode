

def read_parse(fp):
    f = open(fp, 'r')
    res = ''
    with open(fp) as f:
        content = f.readlines()
        content.remove(content[-1])

    for l in content:
        l = l.split(' ')
        

        print(l)
        if l[0] == 'V':
            res += l[1] + ' ' + l[2] + '\n'
        else:
            res += l[1] + '\n'
    return res


print(read_parse('res_c.txt'))