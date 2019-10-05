//package com.vippro.model;
//
//public class Board4AI extends Board {
//
//    @Override
//    public boolean move(int position, boolean direct) {
//        if (super.getCells()[position].getPoint() != 0 && !super.getCells()[position].isKing() && !isFirstRoundWrong(position,
//                direct)) {
//            if (direct) {                //phải
//                int index = (position + 1) % 12;
//                while (super.getCells()[position].getPoint() != 0) {
//                    super.getCells()[index].raisePoint(1);
//                    super.getCells()[position].downPoint(1);
//                    index = (index + 1) % 12;
//                }
//                if (super.getCells()[index].isEmpty())
//                    checkEatFake((index - 1 + 12) % 12, direct);
//                else move(index, direct);
//            } else {                       //trái
//                int index = (position - 1 + 12) % 12;
//                while (super.getCells()[position].getPoint() != 0) {
//                    super.getCells()[index].raisePoint(1);
//                    super.getCells()[position].downPoint(1);
//                    index = (index - 1 + 12) % 12;
//                }
//                if (super.getCells()[index].isEmpty())
//                    checkEatFake((index + 1) % 12, direct);
//                else move(index, direct);
//            }
//            super.setRound(super.getRound()++);;
//            return true;
//        }
//        return false;
//    }
//}
