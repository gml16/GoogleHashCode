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


def calculate_score(_a, _b):
    a = set(_a)
    b = set(_b)
    a.remove('V') if 'V' in a else a.remove('H')
    b.remove('V') if 'V' in b else b.remove('H')
    return min(len(a&b), len(a-b), len(b-a))

    

def merge_slide(slides):
    while (len(slides) > 1):
        slide = slides[0]
        slides.remove(slide)
        best = []
        for j,s in enumerate(slides):
            poss = []
            fb = calculate_score(slide[0], s[-1])
            poss.append(fb)
            bf = calculate_score(slide[-1], s[0])
            poss.append(bf)

            best.append((j, ("fb", fb)) if fb > bf else (j, ("bf", bf)))
        m_best = best.index(max(best, key=lambda x:x[1][1]))
        bst = best[m_best]
        b = slides[bst[0]]
        slides.remove(b)
        if bst[1][0] == "fb":
            for s_i in slide:
                b.append(s_i)
            if len(slides) == 0:
                return b
            slides.append(b)
        else:
            for b_i in b:
                slide.append(b_i)
            if len(slides) == 0:
                return slide
            slides.append(slide)

    return slides



def main():
    a = "../dataset/a_example.txt"
    b = "../dataset/b_lovely_landscapes.txt"
    c = "../dataset/c_memorable_moments.txt"
    d = "../dataset/d_pet_pictures.txt"
    e = "../dataset/e_shiny_selfies.txt"
    filepath = a
    photos = read_input_photo(filepath)
    a = photos[:3]
    b = photos[3:]
    print("1 ",a)
    print("2 ", b, end="\n\n")
    c = merge_slide([a,b])
    print("Slideshow:")
    print(c)


if __name__ == "__main__":
    main()