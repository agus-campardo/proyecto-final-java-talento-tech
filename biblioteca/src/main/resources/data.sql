-- ============================================
-- DATOS INICIALES PARA LA BIBLIOTECA
-- ============================================

-- Insertar libros SIN ESPECIFICAR ID (la BD los asignará automáticamente)
INSERT INTO libro (titulo, autor, isbn, genero, stock, veces_prestado) VALUES
('Cien años de soledad', 'Gabriel García Márquez', '978-0307474723', 'Realismo mágico', 3, 14),
('El nombre de la rosa', 'Umberto Eco', '978-0156001311', 'Misterio', 2, 9),
('La sombra del viento', 'Carlos Ruiz Zafón', '978-8408172177', 'Ficción histórica', 3, 16),
('Rayuela', 'Julio Cortázar', '978-8437602390', 'Ficción', 2, 20),
('El señor de los anillos', 'J.R.R. Tolkien', '978-0261102385', 'Fantasía', 5, 26),
('1984', 'George Orwell', '978-0451524935', 'Distopía', 3, 30),
('Fahrenheit 451', 'Ray Bradbury', '978-1451673319', 'Ciencia ficción', 1, 11),
('Drácula', 'Bram Stoker', '978-0141439846', 'Terror', 4, 18),
('It', 'Stephen King', '978-1501142970', 'Terror', 0, 36),
('La carretera', 'Cormac McCarthy', '978-0307387894', 'Postapocalíptica', 3, 7),
('El principito', 'Antoine de Saint-Exupéry', '978-0156012195', 'Fábula', 9, 51),
('Orgullo y prejuicio', 'Jane Austen', '978-0141439518', 'Romance', 3, 23),
('Crimen y castigo', 'Fiódor Dostoyevski', '978-0486415871', 'Drama', 2, 5),
('Don Quijote de la Mancha', 'Miguel de Cervantes', '978-8424113096', 'Clásico', 5, 40),
('Harry Potter y la piedra filosofal', 'J.K. Rowling', '978-8478884452', 'Fantasía', 7, 102),
('Los juegos del hambre', 'Suzanne Collins', '978-8427202122', 'Distopía', 3, 45),
('El alquimista', 'Paulo Coelho', '978-0061122415', 'Fábula', 6, 60),
('La chica del tren', 'Paula Hawkins', '978-0385682315', 'Thriller', 0, 28),
('El código Da Vinci', 'Dan Brown', '978-0307474273', 'Suspenso', 1, 76),
('Neuromante', 'William Gibson', '978-0441569595', 'Ciencia ficción', 0, 16);

-- ============================================
-- Observación: los préstamos los tenemos que crear manualmente
-- ya que necesitamos saber los ID's que se asignaron a los libros
-- ============================================