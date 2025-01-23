const jpqlQuery = `
select 
  i.id,i.theGeom
from 
  Isochrone i
left outer join fetch i.typeIsochrone t
left outer join fetch i.gare g
where 
  t.id = :typeIsochroneId 
  and 
  g.id = :gareId
`;

/*function parseJPQL(query) {
  // List of SQL keywords
  const sqlKeywords = [
    'select',
    'from',
    'left',
    'outer',
    'join',
    'fetch',
    'where',
    'and',
    'or',
    'inner',
    'right',
    'on',
  ];

  const selectRegex = /select\s+(.*?)\s+from/i;
  const fromRegex = /from\s+([\s\S]+?)(\s+where|\s*$)/i;
  const whereRegex = /where\s+([\s\S]*)/i;
  const paramRegex = /(\w+)\.(\w+)\s*=\s*:(\w+)/g;

  // Extract the select clause
  const selectMatch = query.match(selectRegex);
  const selectFields = selectMatch
    ? selectMatch[1].split(',').map((field) => field.trim())
    : [];

  // Extract the from clause
  const fromMatch = query.match(fromRegex);
  const fromClause = fromMatch ? fromMatch[1] : '';

  // Extract alias and corresponding tables
  const tableAliasMap = {};
  const aliasRegex = /(\w+)\s+(\w+)/g;
  let aliasMatch;

  while ((aliasMatch = aliasRegex.exec(fromClause)) !== null) {
    const table = aliasMatch[1].trim();
    const alias = aliasMatch[2].trim();
    if (!sqlKeywords.includes(alias.toLowerCase())) {
      tableAliasMap[alias] = table;
    }
  }

  // Extract the where clause
  const whereMatch = query.match(whereRegex);
  const whereClause = whereMatch ? whereMatch[1] : '';

  // Extract parameters and create mapping
  let match;
  const params = [];
  const paramMapping = {};

  while ((match = paramRegex.exec(whereClause)) !== null) {
    const alias = match[1].trim();
    const fieldName = match[2].trim();
    const param = match[3].trim();
    params.push(param);
    const table = tableAliasMap[alias] || alias;
    const fullFieldName = `${table}.${fieldName}`;
    paramMapping[param] = fullFieldName;
  }

  return {
    select: selectFields,
    params,
    paramMapping,
    tableAliasMap,
  };
}*/

const parseJPQL = (query) => {
  const tables = ['Isochrone'];
  const sqlKeywords = [
    'select ',
    'from ',
    'left ',
    'outer ',
    'join ',
    'fetch ',
    'where ',
    'and ',
    'or ',
    'inner ',
    'right ',
    'on ',
  ];
  const selectRegex = /select\s+(.*?)\s+from/i;

  const whereRegex = /where\s+([\s\S]*)/i;
  const paramRegex = /(\w+)\.(\w+)\s*=\s*:(\w+)/g;

  const selectMatch = query.match(selectRegex);
  const selectFields = selectMatch
    ? selectMatch[1].split(',').map((field) => field.trim())
    : [];
  // Expressions régulières pour extraire les parties de la requête
  const fromRegex = /from\s+([\s\S]+?)(\s+where|\s*$)/i;

  // Extraction de la partie FROM de la requête
  const fromMatch = query.match(fromRegex);
  let From = fromMatch ? fromMatch[1] : '';

  for (let i = 0; i < sqlKeywords.length; i++) {
    const regex = new RegExp(sqlKeywords[i], 'gi'); // 'gi' pour insensible à la casse et global
    From = From.replace(regex, '');
  }
  const aliasMap = {};
  console.log('=>', selectFields);
  From = From.split('\n');
  for (let i = 0; i < From.length; i++)
    aliasMap[From[i].split(' ')[1]] = From[i].split(' ')[0];
  return console.log(aliasMap);
  console.log(whereRegex);
  let params = query.match(paramRegex);
  for (let i = 0; i < params.length; i++) {
    const param = params[i].split(' = ');
    const tbAlias = param[0].split('.')[0];
    console.log(tbAlias);
    const q = query.split(' ' + tbAlias);
    console.log('-->', q);
  }
};

// Parse the JPQL query
const result = parseJPQL(jpqlQuery);
/*
console.log('Select:', result.select);
console.log('Params:', result.params);
console.log('Param Mapping:', result.paramMapping);
console.log('Alias to Table Mapping:', result.tableAliasMap);
*/
