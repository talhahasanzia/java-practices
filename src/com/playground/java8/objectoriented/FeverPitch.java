package com.playground.java8.objectoriented;


// Polymorphism
public class FeverPitch extends Book {

        // overriding to return original book name regardless of what is saved in the instance

        /**
         * Gets title.
         *
         * @return
         */
        @Override
        public String getTitle() {
            return "Fever Pitch";
        }



        /**
         * Generate ISBN using defaults
         **/
        @Override
        public void generateISBN() {

            // overriding implementation to add numeric characters to isbn


            char[] charset = "acegikmoqsuwy0123456789".toCharArray();

            int max = 22;

            for (int i = 0; i < 8; i++) {

                isbn = isbn + charset[randomWithRange(0, max)];

            }

        }

        /**
         * Generate ISBN based off user provided charset
         *
         * @param charset user provided character array values to generate ISBN from
         */
        public void generateISBN(char[] charset) {
            // overloaded method to allow user to provide his own charset for random isbn generation

            for (int i = 0; i < 8; i++) {

                isbn = isbn + charset[randomWithRange(0, charset.length)];

            }

        }


        /**
         * generate isbn using user provided charset and length
         *
         * @param charset    set (character array) to be used in generation
         * @param isbnLength of isbn how long it will be
         */
        public void generateISBN(char[] charset, int isbnLength) {
            // overloaded method to allow user to provide his own charset
            // and length for random isbn generation


            for (int i = 0; i < isbnLength; i++) {

                isbn = isbn + charset[randomWithRange(0, charset.length)];

            }


        }
    }