# Pattern_Command.Example_1
Использование паттерна Command.
Есть class CPoint для описания точки с двумя координатами, конструктором, который координаты инициализирует, методом для сдвига точки(move).
Вместо прямого использования метода move, для каждой команды перемещения создаётся объект MoveCommand, реализующий паттерн.
