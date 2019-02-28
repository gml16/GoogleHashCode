import copy
import numpy as np
import matplotlib.pyplot as plt
# from keras.models import Sequential
# from keras.layers import Dense
# from sklearn.model_selection import KFold

def read_input_photo(filename):

    lines = open(filename).readlines()

    ps = [str(L).split(' ') for L in lines[1:]]
    p = []
    for i,_p in enumerate(ps):
        p.append(_p) 
        p[i][-1] = (p[i][-1])[:-1]
        p[i] = list(filter(lambda x: not x.isdigit(), p[i]))
        p[i].append(i)
        p[i] = set(p[i])
    return p


def calculate_score(a, b):
    return min(len(a&b), len(a-b), len(b-a))


def join_verticals(photos):
    for photo in photos:
        if 'H' in photo:
            photos.remove(photo)

    pairs=[]

    for p1 in photos:
        max_sc = 0
        max_pair = p1
        photos.remove(p1)
        for p2 in photos:
            sc = len(p1.union(p2))
            if sc > max_sc:
                max_sc = sc
                max_pair = p2
        pairs.append((p1, max_pair))
        photos.remove(max_pair)

    return pairs



def greedy_slideshow(photos):
    vertical_pairs = join_verticals(copy.copy(photos))
    photos = filter(lambda p: 'H' in p, photos)

    for x,y in vertical_pairs:
        photos.append(x.union(y))

    slideshow = []

    while len(photos) > 0:
        p1 = photos[0]
        slideshow.append(p1)
        max_sc = 0
        max_next = p1
        photos.remove(p1)
        if len(photos) == 0:
            return slideshow

        for p2 in photos:
            sc = calculate_score(p1, p2)
            if sc > max_sc:
                max_sc = sc
                max_next = p2

        slideshow.append(max_next)
        photos.remove(max_next)

    return slideshow




def main():
    a = "../dataset/a_example.txt"
    b = "../dataset/b_lovely_landscapes.txt"
    c = "../dataset/c_memorable_moments.txt"
    d = "../dataset/d_pet_pictures.txt"
    e = "../dataset/e_shiny_selfies.txt"
    filepath = c
    photos = read_input_photo(filepath)
    #print(photos)
    print(greedy_slideshow(photos))



if __name__ == "__main__":
    main()