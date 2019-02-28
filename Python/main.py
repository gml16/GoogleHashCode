import tqdm 
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


def calculate_score(s):
    N = len(s)
    if (N <= 1):
        return 0
    score = 0
    for i in range(N-1):
        a = s[i]
        b = s[i+1]
        score += min(len(a&b), len(a-b), len(b-a)) 
    return score

def merge_slide(slide, depth=1):
    for p in slide:
        print(p)






def main():
    a = "../dataset/a_example.txt"
    b = "../dataset/b_lovely_landscapes.txt"
    c = "../dataset/c_memorable_moments.txt"
    d = "../dataset/d_pet_pictures.txt"
    e = "../dataset/e_shiny_selfies.txt"
    filepath = a
    photos = read_input_photo(filepath)
    merge_slide(photos)
    print(photos)
    print("Slideshow:")



if __name__ == "__main__":
    main()