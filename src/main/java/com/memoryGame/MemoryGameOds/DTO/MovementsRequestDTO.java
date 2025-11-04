package com.memoryGame.MemoryGameOds.DTO;

public class MovementsRequestDTO {
        private boolean isActive;
        private int timeSecond;

        public boolean getIsActive() {
            return isActive;
        }

        public void setIsActive(boolean isActive) {
            this.isActive = isActive;
        }

        public int getTimeSecond() {
            return timeSecond;
        }

        public void setTimeSecond(int timeSecond) {
            this.timeSecond = timeSecond;
        }
}
