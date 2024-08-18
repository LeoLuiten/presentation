INSERT INTO players (id ,user_name, password, email, avatar, last_login, date_created, updated_at)
VALUES (1000000,'APP', null, null, null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO players (id ,user_name, password, email, avatar, last_login, date_created, updated_at)
VALUES (100, 'lluiten', 'Password#03', 'email2@email.com', null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO games (id, code, name, description, rules)
VALUES (1000000, 'RPS', 'Rock Paper Scissors',
        'Rock, Paper, Scissors is a simple hand game typically played between two individuals. ' ||
        'The game involves each player simultaneously forming one of the three shapes with their hand: ' ||
        'a closed fist representing a rock, an open hand with the palm facing downwards representing ' ||
        'a sheet of paper, or an extended index and middle finger forming ' ||
        'a V shape to represent scissors. ' ||
        'To play this game we will replace the shapes of the hands with letters, ' ||
        'R for rock, P for papper and S for scissors. ',
        'The objective of the game is to defeat the opponent by selecting a hand shape that defeats their choice according to a set of predetermined rules: ' ||
        'rock crushes scissors, scissors cuts paper and paper cover rock. ' ||
        'The outcome of each round is determined by comparing the hand shapes, ' ||
        'and the player with the winning shape scores a point.');

INSERT INTO matches(id, game_id, player_id, created_at, updated_at, status)
VALUES (1000000, 1000000, 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STARTED');
INSERT INTO matches(id, game_id, player_id, created_at, updated_at, status)
VALUES (1000001, 1000000, 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'FINISHED');
INSERT INTO matches(id, game_id, player_id, created_at, updated_at, status)
VALUES (1000002, 1000000, 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'CANCELED');