package com.playground.java8.objectoriented;


// Polymorphism
public class Book {

        protected String title;
        protected String isbn;

        /**
         * Returns title set by user
         *
         * @return Title of the book
         */
        public String getTitle() {
            return title;
        }

        /**
         * Gets ISBN. {@link #generateISBN()} or {@link #setIsbn(String)} must be called before this call.
         *
         * @return isbn
         */
        public String getIsbn() {
            return isbn;
        }

        /**
         * If user wants to add his/her own isbn with this book
         * @param isbn
         */
        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        /**
         * Default method implementation for getting ISBN
         */
        public void generateISBN() {

            char[] charset = "acegikmoqsuwy".toCharArray();

            int max = 12;

            for (int i = 0; i < 8; i++) {

                isbn = isbn + charset[randomWithRange(0, max)];

            }

        }

        int randomWithRange(int min, int max) {
            int range = (max - min) + 1;
            return (int) (Math.random() * range) + min;
        }

    }
