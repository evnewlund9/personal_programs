import rankandSuitCount

def poker(hand):
    rank_list = ['A','2','3','4','5','6','7','8','9','10','J','Q','K']
    suit_list = ['C','S','D','H']
    rank, suit = rankandSuitCount.rankandSuitCount(hand)
    if len(rank) == 5:
        if len(suit) == 1:
            locations = []
            for i in rank:
                locations.append(rank_list.find(i))
            if max(locations) - min(locations) == 5:
                return "straight flush"
            else:
                return "flush"
        elif len(suit) == 5:
            if max(locations) - min(locations) == 5:
                return "straight"
            else:
                return "high-card"
    elif len(rank) == 4:
        return "One pair"
    elif len(rank) == 3:
        is3 = False
        for i in suit:
            if i == 3:
                is3 = True
        if is3:
            return "three-of-a-kind"
        else:
            return "two pair"
    elif len(rank) == 2:
        is4 = False
        for i in rank:
            if i == 4:
                is4 = True
        if is4:
            return "four-of-a-kind"
        else:
            return "full-house"

def main():
    hand = ['8D','9D','TD','JD','QD']
    print(poker(hand))

if __name__=="__main__":
    main()
