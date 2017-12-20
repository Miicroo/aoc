function countConfigIterations(input) {
	const seenConfigs = getConfigLoop(input);
	return seenConfigs.length-1;
}

function getConfigLoop(input) {
	let config = input.slice(0);
	const seenConfigs = [];
	while(seenConfigs.indexOf(configRepresentation(config)) === -1) {
		seenConfigs.push(configRepresentation(config));
		config = nextConfig(config);
	}
	seenConfigs.push(configRepresentation(config));

	return seenConfigs;
}

function configRepresentation(config) {
	return config.join(',');
}

function nextConfig(oldConfig) {
	let config = oldConfig.slice(0);
	const max = findMax(config);
	config[max.index] = 0;
	for(let i = 1; i<=max.value; i++) {
		const currentIndex = (max.index + i) % config.length;
		config[currentIndex]++;
	}

	return config;
}

function findMax(arr) {
	return arr.reduce((result, current, index) => {
						if(current > result.value) {
							return {'value':current,'index':index};
						} else {
							return result;
						}
					}, {'value':-1,'index':-1});
}

const input = '4	10	4	1	8	4	9	14	5	1	14	15	0	15	3	5'.split('	')
					.map(s => parseInt(s));
console.log(countConfigIterations(input));

function countConfigIterations2(arr) {
	const loop = getConfigLoop(arr);
	const loopingItem = loop[loop.length-1];
	const firstLoopItemIndex = loop.indexOf(loopingItem);
	return loop.length - firstLoopItemIndex -1;
}
console.log(countConfigIterations2(input));
