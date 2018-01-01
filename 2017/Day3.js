function getRange(num) {
	const intSqrt = Math.floor(Math.sqrt(num));
	const lowBoundary = intSqrt % 2 == 0 ? intSqrt-1 : intSqrt; // Low boundary is the odd int that is closest to sqrt(num)
	const highBoundary = lowBoundary*lowBoundary == num ? lowBoundary : lowBoundary + 2; // High boundary is either low+2 or same as low (in case of sqrt(num) being an int (we reached end of square)).

	return [lowBoundary, highBoundary];
}

function traverseSquare(boundary, aimNum) {
	const highBoundary = boundary[1];
	const sideLength = (highBoundary-1); // Side length of square
	const distance = sideLength / 2; // Current dist to position

	const maxSquare = highBoundary * highBoundary;

	// Each of the corners (whole multiple of sideLength from max), is equally far from position.
	let distanceFromCorner = (maxSquare-aimNum) % sideLength;

	return distance + Math.abs(distance-distanceFromCorner);
}

const input = 289326;
const boundary = getRange(input);
const distance = traverseSquare(boundary, input);

function generateGrid(size) {
	return Array(size).fill().map(i => Array(size).fill().map(i => 0));
}

function fillGrid(grid, x, y, direction, maxValue) {
	const nextDirections = {'down': 'right', 'right': 'up', 'up': 'left', 'left': 'down'};
	
	while(x != grid.length && y != grid.length) {
		const nextDir = nextDirections[direction];
		const shouldChangeDirection = canGoTo(grid, x, y, nextDir);
		if(shouldChangeDirection) {
			direction = nextDir;
		}

		let nextPosition = getNextPosition(x, y, direction);
		x = nextPosition.x;
		y = nextPosition.y;
		grid[y][x] = getValue(grid, nextPosition.x, nextPosition.y);

		if(grid[y][x] > maxValue) {
			break;
		}
	}

	return grid;
}

function canGoTo(grid, x, y, newDirection) {
	const next = getNextPosition(x, y, newDirection);
	return grid[next.y][next.x] === 0;
}

function getNextPosition(x, y, direction) {
	let nextX = x;
	let nextY = y;

	if(direction === 'right') {
		nextX++;
	} else if(direction === 'left') {
		nextX--;
	} else if(direction === 'up') {
		nextY--;
	} else if(direction === 'down') {
		nextY++;
	}
	return {'x':nextX, 'y': nextY};
}

function getValue(grid, x, y) {
	return getValueOr0(grid,x-1,y-1) + getValueOr0(grid,x-1,y) + getValueOr0(grid,x-1,y+1) + 
			getValueOr0(grid,x,y-1) + getValueOr0(grid,x,y+1) + 
			getValueOr0(grid,x+1,y-1) + getValueOr0(grid,x+1,y) + getValueOr0(grid,x+1,y+1);
}

function getValueOr0(grid, x, y) {
	return grid[y] ? (grid[y][x] ? grid[y][x] : 0) : 0;
}

size = 9;
let x = y = (size-1) / 2;
const grid = generateGrid(size);
grid[y][x] = 1;

fillGrid(grid, x, y, 'down', input); // Find max in array => answer