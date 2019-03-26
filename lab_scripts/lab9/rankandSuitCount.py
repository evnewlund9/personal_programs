def rankandSuitCount(hand):
    card_rank = {}
    card_suit = {}
    for card in hand:
        if card[0] not in card_rank:
            card_rank[card[0]] = 1
        else:
            card_rank[card[0]] += 1
        if card[1] not in card_suit:
            card_suit[card[1]] = 1
        else:
            card_suit[card[1]] += 1
    return card_rank, card_suit

def main():
    rank, suit = rankandSuitCount(['AS','AD','2C','TH','TS'])
    print(rank)
    print(suit)

if __name__ == '__main__':
    main()
