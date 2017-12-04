function getRange(num) {
	const intSqrt = Math.floor(Math.sqrt(num));
	const lowBoundary = intSqrt % 2 == 0 ? intSqrt-1 : intSqrt; // Low boundary is the odd int that is closest to sqrt(num)
	const highBoundary = lowBoundary*lowBoundary == num ? lowBoundary : lowBoundary + 2; // High boundary is either low+2 or same as low (in case of sqrt(num) being an int (we reached end of square)).

	return [lowBoundary, highBoundary];
}

function traverseSquare(boundary, aimNum) {
	const highBoundary = boundary[1];
	const sideLength = (highBoundary-1); // Side length of square
	const distance = sideLength / 2; // Current dist to origo

	const maxSquare = highBoundary * highBoundary;

	// Each of the corners (whole multiple of sideLength from max), is equally far from origo.
	let distanceFromCorner = (maxSquare-aimNum) % sideLength;

	return distance + Math.abs(distance-distanceFromCorner);
}

const input = 289326;
const boundary = getRange(input);
const distance = traverseSquare(boundary, input);
