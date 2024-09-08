from util import ordered_face, face_map, face_to_cycles_map, identifier_to_color, ordered_colors

class Cube:
    def __init__(self, initial_state=None):
        if initial_state and len(initial_state) == 54:
            self.cube = initial_state
        else:
            self.cube = [-1] * 54
            self.initialize_faces()

    def initialize_faces(self):
        for face in range(6):
            for unit in range(9):
                self.cube[face * 9 + ordered_face[unit]] = self.get_default_face_color(face)

    def get_default_face_color(self, face):
        return ordered_colors[face]

    def get_cube(self):
        return self.cube

    def transform_to_spiral(self, indices):
        return [(ordered_face[index % 9] + (index - (index % 9))) for index in indices]

    def cycle_swap(self, indices):
        transformed_indices = self.transform_to_spiral(indices)
        step = len(indices) // 4
        home = [self.cube[i] for i in transformed_indices]

        for i in range(len(indices)):
            self.cube[transformed_indices[(i + step) % len(indices)]] = home[i]

    def algorithm(self, moves):
        for move in moves:
            counterclockwise = move.endswith('p')
            face = face_map.get(move.rstrip('p'))
            if face is None:
                raise ValueError(f"Invalid move: {move}")
            self.transform(face, counterclockwise)

    def transform(self, face, counterclockwise=False):
        cycles = face_to_cycles_map.get(face)
        if cycles is None:
            return

        for cycle in (cycle[::-1] if counterclockwise else cycle for cycle in cycles):
            self.cycle_swap(cycle)

    def face_to_string(self, face_start_index):
        return '\n'.join(
            f"{identifier_to_color[self.cube[face_start_index + i]]} "
            f"{identifier_to_color[self.cube[face_start_index + i + 1]]} "
            f"{identifier_to_color[self.cube[face_start_index + i + 2]]}"
            for i in range(0, 9, 3)
        ) + '\n'
        
    def get_top_face(self):
        return self.face_to_string(0)

    def __str__(self):
        return '\n'.join(
            self.face_to_string(face * 9)
            for face in range(6)
        )
        
        